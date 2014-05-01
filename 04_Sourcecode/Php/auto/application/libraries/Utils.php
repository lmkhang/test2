<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

/**
 * Created by IntelliJ IDEA.
 * User: Windows
 * Date: 30/03/2014
 * Time: 20:30
 */
class Utils {

    protected $_MY_Controller;

    public function __construct() {
        // Call the Model constructor
        $this->_MY_Controller = & MY_Controller::get_instance();
    }

    public function response($code) {
        switch ($code) {
            case RS_SUCCESS:
                $text = "Success";
                break;
            case RS_INVALID_PARAMS:
                $text = "INVALID PARAMETERS";
                break;
            case RS_FAILED:
                $text = "FAILED";
                break;
            default:
                $text = "Unknown error";
                break;
        }
        return array(LK => array(LK_RESULKT => array("CODE" => $code, "MESSAGE" => $text)));
    }

    public function createJson($code, $array = array()) {
        header("Content-type: application/json; charset=utf-8");
        $response = $this->response($code);
        if (!empty($array)) {
            $response = array_merge($response[LK], $array);
            $response = array(LK => $response);
        }
        
        $response = json_encode($response);
        echo $response;
        exit();
    }

    public function checkParams($post, $arrayKey) {
        $flag = true;
        foreach ($arrayKey as $v) {
            if ($post[$v] == "" || $post[$v] == NULL || !isset($post[$v])) {
                $flag = false;
                break;
            }
        }

        if (!$flag) {
            $this->createJson(RS_INVALID_PARAMS);
            exit();
        }
        return $post;
    }

}

?>