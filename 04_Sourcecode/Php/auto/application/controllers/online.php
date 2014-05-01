<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class online extends MY_Controller {

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
        $this->load->model('monline');
    }

    public function index() {
        $this->_utils->checkParams($this->post(), array('status'));
        $status = $this->post('status');
        $objs = $this->monline->getOnline($status);
        $count = count($objs);
        $this->_utils->createJson(RS_SUCCESS, array(LK_ONLINE => $objs, LK_TOTAL => $count));
        return;
    }

    public function update_status() {
        $this->_utils->checkParams($this->get(), array('status'));
        $status = $this->get('status');
        $this->monline->update_status($status);
        $this->_utils->createJson(RS_SUCCESS);
        return;
    }

    public function update() {
        //check parameters
        $this->_utils->checkParams($this->post(), array('userid', 'address'));
        //check exists
        $where = array("status" => 1, "address" => $this->post("address"), "userid" => $this->post("userid"));
        $check = $this->monline->count_all($where);
        $date = date("Y-m-d H:i:s");
        if ($check > 0) {
            $rs = $this->monline->update(array("updated" => $date), array("userid" => $this->post('userid'), "address" => $this->post('address')));
        } else {
            $rs = $this->monline->insert(array("userid" => $this->post('userid'), "address" => $this->post('address'), "created" => $date, "updated" => $date));
        }
        if ($rs) {
            $this->_utils->createJson(RS_SUCCESS);
        } else {
            $this->_utils->createJson(RS_FAILED);
        }
    }

}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */