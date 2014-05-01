<?php
/**
 * Created by IntelliJ IDEA.
 * User: Windows
 * Date: 30/03/2014
 * Time: 16:10
 */

class MY_Exceptions extends CI_Exceptions {
    /**
     * Controller
     *
     * @access public
     */
    function My_Exceptions()
    {
        parent::__construct();
    }

    /**
     * General Error Page
     *
     * @access    private
     * @param    string    disabled - there to keep ci happy
     * @param    string    ditto for this one
     * @param    string    the error function name
     * @return    string
     */
    function show_error($heading, $message, $template = 'error_general', $status_code = 404)
    {
        //Ghi lại nhật ký lỗi

        $log = FCPATH . "error_logs/" . date("Y-m-d") . "-" . $template . ".log";

        $date = new DateTime();
        $date = $date->format('Y-m-d H:i:s');

        $text = "";
        $text .= "\n>>> FOUND ERROR at $date <<<";
        $text .= "\n\t\tHeading: $heading";
        $text .= "\n\t\tMessage: " . $this->createMessage($message);
        $text .= "\n\t\tError code: $template";
        $text .= "\n\t\tError number: $status_code";
        $text .= "\n\t\tServer info: " . var_export($_SERVER, true);
        if (!empty($_REQUEST))
            $text .= "\n\t\tRequest info: " . var_export($_REQUEST, true);
        if (!empty($_POST))
            $text .= "\n\t\tPost value: " . var_export($_POST, true);
        if (!empty($_GET))
            $text .= "\n\t\tGet value: " . var_export($_GET, true);
        $text .= "\n>>> END ERROR at $date <<<\n";

        $f = fopen($log, "a");
        fwrite($f, $text);
        fclose($f);
        //kết thúc việc ghi nhật ký lỗi

        if (ENVIRONMENT !== 'development')//Nếu website của bạn không phải đang hoạt động ở chế độ debug thì thay đổi báo lỗi để người dùng không biết thực sự lỗi là gì, điều này rất hữu ích cho việc bảo mật website của bạn
        {
            $heading = "Máy chủ đang quá tải";
            $message = "Lỗi.";
        }
        return parent::show_error($heading, $message, $template, $status_code);//Trả về nội dung để hiển thị cho lỗi tương ứng
    }

    protected function createMessage($message){
        return $message;
    }
} 