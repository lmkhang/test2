<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class mlink extends MY_Model {

    protected $table = "lk_link";

    public function __construct() {
        parent::__construct($this->table);
    }

    public function getLinks($userid, $status = "1") {
        //sql
        $sub_sql = "SELECT id_link FROM lk_link_liked"
                . " WHERE userid = '" . $userid . "' "
                . " AND status='" . $status . "' ";
        $query = "SELECT * FROM $this->table as l
                    WHERE l.status = $status
                        AND l.id NOT IN (" . $sub_sql . ") 
                    ORDER BY l.id DESC ";
        $objects = $this->query($query);
        return $objects;
    }
    
}
