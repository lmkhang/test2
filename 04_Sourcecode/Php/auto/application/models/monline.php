<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class monline extends MY_Model {

    protected $table = "lk_online";

    public function __construct() {
        parent::__construct($this->table);
    }

    public function getOnline($status = "1") {
        $_second = 60 * ONLINE_CHECK_TIME;
        $currentDate = date("Y-m-d H:i:s");
        //sql
        $query = "SELECT *, TIMESTAMPDIFF(SECOND, o.updated, '" . $currentDate . "') AS second FROM $this->table as o
                    WHERE status = $status
                    HAVING second<= " . $_second . "
                    ORDER BY o.updated DESC ";
        $objects = $this->query($query);
        return $objects;
    }

    public function update_status($status = "1") {
        $objects = $this->getOnline($status);
        if (count($objects) > 0) {
            //update status=0
            $strIds = "";
            foreach ($objects as $k => $v) {
                $strIds .= $v['id'] . ", ";
            }
            $strIds = rtrim($strIds, ", ");
            $queryUpdate = "UPDATE $this->table SET status=2
                    WHERE id NOT IN (" . $strIds . ")";
            $result = $this->queryUpdate($queryUpdate);
        } else {
            $result = array();
        }

        return $result;
    }

}
