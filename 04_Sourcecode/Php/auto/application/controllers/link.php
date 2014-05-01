<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class link extends MY_Controller {

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
        $this->load->model('mlink');
    }

    public function index() {
        $this->_utils->checkParams($this->post(), array('userid', 'status'));
        $status = $this->post('status');
        $userid = $this->post('userid');
        $objs = $this->mlink->getLinks($userid, $status);
        $count = count($objs);
        $this->_utils->createJson(RS_SUCCESS, array(LK_LINK => $objs, LK_TOTAL => $count));
        return;
    }

}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */