<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class link_liked extends MY_Controller {

    /**
     * Index Page for this controller.
     *
     * Maps to the following URL
     *        http://example.com/index.php/welcome
     *    - or -
     *        http://example.com/index.php/welcome/index
     *    - or -
     * Since this controller is set as the default controller in
     * config/routes.php, it's displayed at http://example.com/
     *
     * So any other public methods not prefixed with an underscore will
     * map to /index.php/welcome/<method_name>
     * @see http://codeigniter.com/user_guide/general/urls.html
     */
    public function __construct() {
        parent::__construct();
        $this->load->model('mlink_liked');
    }

    public function liked() {
        $this->_utils->checkParams($this->post(), array('userid', 'id', 'status'));
        $status = $this->post('status');
        $id = $this->post('id');
        $userid = $this->post('userid');
        $result = $this->mlink_liked->liked($userid, $id, $status);
        if ($result) {
            $this->_utils->createJson(RS_SUCCESS);
        } else {
            $this->_utils->createJson(RS_FAILED);
        }
        return;
    }

}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */