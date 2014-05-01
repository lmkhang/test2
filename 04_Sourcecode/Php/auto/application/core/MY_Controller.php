<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

/**
 * @property CI_Loader $load
 * @property CI_Form_validation $form_validation
 * @property CI_Input $input
 * @property CI_Email $email
 * @property CI_DB_active_record $db
 * @property CI_DB_forge $dbforge
 * @property CI_Table $table
 * @property CI_Session $session
 * @property CI_FTP $ftp
 *
 */
class MY_Controller extends CI_Controller
{
    protected $class_name;
    protected $function_name;
    protected $_utils;

    public function __construct()
    {
        // Call the Model constructor
        parent::__construct();
        $this->_utils = new Utils();
    }

    public function post($key = '', $value = '')
    {
        if ($key != '') {
            return $this->input->post($key, TRUE);
        }
        return $this->input->post();
    }

    public function get($key = '', $value = '')
    {
        if ($key != '') {
            return $this->input->get($key, TRUE);
        }
        return $this->input->get();
    }

    public function ip_address(){
        return $this->input->ip_address();
    }

    public function valid_ip($ip){
        return $this->input->valid_ip($ip);
    }

    public function _remap($method, $params = array())
    {
        $this->thietLapBienMoiTruong($method);

        if (!$this->checkPhanQuyen($this->class_name, $this->function_name)) //Không có quyền vào trang này
        {
            show_error('Bạn không có quyền vào trang này', 404);
            return;
        }

        if (method_exists($this, $method)) //Có phương thức đã chỉ định
        {
            $result = @call_user_func_array(array($this, $method), $params);

            if ($result === FALSE) //Không gọi được phương thức đã chỉ định
            {
                show_404();
            }
        } else //Phương thức đã chỉ định không có thực => hiện trang báo lỗi
            show_404();
    }

    protected function thietLapBienMoiTruong($method)
    {
        $this->class_name = strtolower(get_class($this));
        $this->function_name = strtolower($method);
    }

    protected function checkLogin()
    {
        return true;
    }

    protected function checkPhanQuyen($module, $method)
    {
        return true;
    }


}