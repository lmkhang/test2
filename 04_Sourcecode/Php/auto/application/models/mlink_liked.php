<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class mlink_liked extends MY_Model {

    protected $table = "lk_link_liked";

    public function __construct() {
        parent::__construct($this->table);
    }

    public function liked($userid, $id, $status = "1") {
        $date = date("Y-m-d H:i:s");
        return $this->insert(array("userid" => $userid, 'id_link' => $id, 'status' => $status, 'created' => $date, 'updated' => $date));
    }

}
