<?php
namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;

final class ChartsController extends BaseController
{

    public function jsonchartdata(Request $request, Response $response, $args) {
       $json = '{
        "cols": [
        {"id":"","label":"year","type":"string"},
        {"id":"","label":"sales","type":"number"},
        {"id":"","label":"sales","type":"number"},
        {"id":"","label":"sales","type":"number"},
        {"id":"","label":"sales","type":"number"},
        {"id":"","label":"expenses","type":"number"}
        ],
        "rows": [
        {"c":[{"v":"2001"},{"v":3},{"v":5}]},
        {"c":[{"v":"2002"},{"v":5},{"v":10}]},
        {"c":[{"v":"2003"},{"v":6},{"v":4}]},
        {"c":[{"v":"2004"},{"v":8},{"v":32}]},
        {"c":[{"v":"2005"},{"v":3},{"v":56}]}
        ]
        }';

        //$json = '{"cols":[{"air_id":"","label":"date\/time","type":"string"},{"air_id":"","label":"AQI_PM","type":"number"},{"air_id":"","label":"AQI_CO","type":"number"},{"air_id":"","label":"AQI_NO2","type":"number"},{"air_id":"","label":"AQI_SO2","type":"number"},{"air_id":"","label":"AQI_O3","type":"number"}],"rows":[{"c":[{"v":"2019-02-21 11:28:17"},{"v":21},{"v":1},{"v":152},{"v":44},{"v":146}]},{"c":[{"v":"2019-02-21 11:28:24"},{"v":16},{"v":2},{"v":161},{"v":78},{"v":116}]},{"c":[{"v":"2019-02-21 11:28:30"},{"v":15},{"v":2},{"v":152},{"v":50},{"v":97}]},{"c":[{"v":"2019-02-21 11:28:37"},{"v":18},{"v":1},{"v":160},{"v":66},{"v":62}]},{"c":[{"v":"2019-02-21 11:28:43"},{"v":24},{"v":1},{"v":149},{"v":42},{"v":148}]},{"c":[{"v":"2019-02-21 11:28:50"},{"v":18},{"v":1},{"v":154},{"v":51},{"v":117}]},{"c":[{"v":"2019-02-21 11:28:56"},{"v":15},{"v":1},{"v":154},{"v":83},{"v":136}]},{"c":[{"v":"2019-02-21 11:29:03"},{"v":18},{"v":2},{"v":149},{"v":79},{"v":118}]},{"c":[{"v":"2019-02-21 11:29:09"},{"v":23},{"v":1},{"v":156},{"v":67},{"v":147}]},{"c":[{"v":"2019-02-21 11:29:16"},{"v":17},{"v":2},{"v":156},{"v":71},{"v":136}]},{"c":[{"v":"2019-02-21 11:29:22"},{"v":14},{"v":1},{"v":149},{"v":41},{"v":93}]},{"c":[{"v":"2019-02-21 11:29:29"},{"v":19},{"v":1},{"v":150},{"v":56},{"v":125}]},{"c":[{"v":"2019-02-21 11:29:35"},{"v":21},{"v":1},{"v":162},{"v":97},{"v":125}]},{"c":[{"v":"2019-02-21 11:29:42"},{"v":15},{"v":1},{"v":156},{"v":40},{"v":126}]},{"c":[{"v":"2019-02-21 11:29:48"},{"v":22},{"v":1},{"v":153},{"v":58},{"v":113}]},{"c":[{"v":"2019-02-21 11:56:46"},{"v":188},{"v":1},{"v":150},{"v":86},{"v":203}]},{"c":[{"v":"2019-02-21 11:56:53"},{"v":188},{"v":1},{"v":146},{"v":69},{"v":96}]},{"c":[{"v":"2019-02-21 11:56:59"},{"v":188},{"v":1},{"v":148},{"v":51},{"v":66}]},{"c":[{"v":"2019-02-21 11:57:06"},{"v":188},{"v":1},{"v":151},{"v":29},{"v":42}]},{"c":[{"v":"2019-02-21 11:57:12"},{"v":188},{"v":1},{"v":143},{"v":66},{"v":33}]},{"c":[{"v":"2019-02-21 11:57:19"},{"v":188},{"v":1},{"v":147},{"v":42},{"v":41}]},{"c":[{"v":"2019-02-21 11:57:25"},{"v":188},{"v":1},{"v":146},{"v":24},{"v":40}]},{"c":[{"v":"2019-02-21 11:57:32"},{"v":188},{"v":1},{"v":154},{"v":51},{"v":42}]},{"c":[{"v":"2019-02-21 11:57:38"},{"v":188},{"v":1},{"v":153},{"v":35},{"v":42}]},{"c":[{"v":"2019-02-21 11:57:45"},{"v":188},{"v":1},{"v":149},{"v":49},{"v":48}]},{"c":[{"v":"2019-02-21 11:57:51"},{"v":102},{"v":1},{"v":151},{"v":64},{"v":43}]},{"c":[{"v":"2019-02-21 11:57:58"},{"v":188},{"v":1},{"v":149},{"v":70},{"v":67}]},{"c":[{"v":"2019-02-21 11:58:04"},{"v":188},{"v":1},{"v":149},{"v":49},{"v":48}]},{"c":[{"v":"2019-02-21 11:58:11"},{"v":188},{"v":1},{"v":156},{"v":74},{"v":34}]},{"c":[{"v":"2019-02-21 11:58:17"},{"v":188},{"v":1},{"v":151},{"v":25},{"v":41}]},{"c":[{"v":"2019-02-21 11:58:24"},{"v":188},{"v":1},{"v":147},{"v":63},{"v":63}]},{"c":[{"v":"2019-02-21 11:58:30"},{"v":188},{"v":1},{"v":144},{"v":28},{"v":44}]},{"c":[{"v":"2019-02-21 11:58:37"},{"v":188},{"v":1},{"v":145},{"v":43},{"v":81}]},{"c":[{"v":"2019-02-21 11:58:43"},{"v":188},{"v":1},{"v":153},{"v":60},{"v":140}]},{"c":[{"v":"2019-02-21 11:58:50"},{"v":188},{"v":1},{"v":146},{"v":10},{"v":98}]},{"c":[{"v":"2019-02-21 11:58:56"},{"v":188},{"v":1},{"v":144},{"v":45},{"v":102}]},{"c":[{"v":"2019-02-21 11:59:03"},{"v":188},{"v":1},{"v":147},{"v":41},{"v":69}]},{"c":[{"v":"2019-02-21 11:59:09"},{"v":188},{"v":1},{"v":146},{"v":65},{"v":139}]},{"c":[{"v":"2019-02-21 11:59:16"},{"v":188},{"v":1},{"v":149},{"v":26},{"v":107}]},{"c":[{"v":"2019-02-21 11:59:22"},{"v":188},{"v":1},{"v":152},{"v":33},{"v":57}]},{"c":[{"v":"2019-02-21 11:59:29"},{"v":2},{"v":1},{"v":149},{"v":25},{"v":35}]},{"c":[{"v":"2019-02-21 11:59:35"},{"v":2},{"v":1},{"v":150},{"v":62},{"v":47}]},{"c":[{"v":"2019-02-21 11:59:42"},{"v":188},{"v":1},{"v":154},{"v":41},{"v":88}]},{"c":[{"v":"2019-02-21 11:59:48"},{"v":188},{"v":1},{"v":155},{"v":30},{"v":50}]},{"c":[{"v":"2019-02-21 11:59:55"},{"v":188},{"v":1},{"v":154},{"v":41},{"v":80}]},{"c":[{"v":"2019-02-21 12:00:01"},{"v":188},{"v":1},{"v":155},{"v":46},{"v":62}]},{"c":[{"v":"2019-02-21 12:00:08"},{"v":188},{"v":1},{"v":141},{"v":57},{"v":72}]},{"c":[{"v":"2019-02-21 12:00:14"},{"v":188},{"v":1},{"v":145},{"v":47},{"v":106}]},{"c":[{"v":"2019-02-21 12:00:21"},{"v":188},{"v":1},{"v":148},{"v":70},{"v":50}]},{"c":[{"v":"2019-02-21 12:00:27"},{"v":188},{"v":1},{"v":145},{"v":39},{"v":52}]},{"c":[{"v":"2019-02-21 12:00:34"},{"v":188},{"v":1},{"v":152},{"v":52},{"v":50}]},{"c":[{"v":"2019-02-21 12:00:40"},{"v":188},{"v":1},{"v":157},{"v":37},{"v":67}]},{"c":[{"v":"2019-02-21 12:00:47"},{"v":188},{"v":1},{"v":143},{"v":101},{"v":57}]},{"c":[{"v":"2019-02-21 12:00:53"},{"v":22},{"v":1},{"v":144},{"v":58},{"v":46}]},{"c":[{"v":"2019-02-21 12:01:00"},{"v":16},{"v":1},{"v":151},{"v":51},{"v":47}]},{"c":[{"v":"2019-02-21 12:01:06"},{"v":7},{"v":1},{"v":153},{"v":20},{"v":45}]},{"c":[{"v":"2019-02-21 12:01:13"},{"v":6},{"v":2},{"v":141},{"v":69},{"v":153}]},{"c":[{"v":"2019-02-21 12:01:19"},{"v":10},{"v":2},{"v":141},{"v":45},{"v":115}]}]}' ;       
        return $response->withHeader('Content-type', 'application/json')
              ->withStatus(200)
              ->write($json);
    }

    public function dynamic_chart_json(Request $request, Response $response, $args) {
        $sql = "SELECT * from udoo_data";
        
        try {
            $sth = $this->db->query($sql);
            $result = $sth->fetchAll();

            if ($result) {
                // build array for Column labels
                $json_array['cols'] = array(
                        array('id'=>'', 'label'=>'date/time', 'type'=>'string'),
                        array('id'=>'', 'label'=>'CO2', 'type'=>'number'),
                        array('id'=>'', 'label'=>'SO2', 'type'=>'number'),
                        array('id'=>'', 'label'=>'NO2', 'type'=>'number'),
                        array('id'=>'', 'label'=>'temp', 'type'=>'number'),
                        array('id'=>'', 'label'=>'pm25', 'type'=>'number'));

                // loop thru the sensor data and build sensor_array
                foreach ($result as $row) {
                    $sensor_array = array();
                    $sensor_array[] = array('v'=>$row['datetime']);
                    $sensor_array[] = array('v'=>$row['s1']);
                    $sensor_array[] = array('v'=>$row['s2']);
                    $sensor_array[] = array('v'=>$row['s3']);
                    $sensor_array[] = array('v'=>$row['s4']);
                    $sensor_array[] = array('v'=>$row['s5']);
                   
                    // add current sensor_array line to $rows
                    $rows[] = array('c'=>$sensor_array);
                }
            
                // add $rows to $json_array
                $json_array['rows'] = $rows;
                
              return $response->withHeader('Content-type', 'application/json')
              ->write(json_encode($json_array, JSON_NUMERIC_CHECK))
              ->withStatus(200);

            } else {
                $response = $response->withStatus(404);
            }
        } catch(PDOException $e) {
            echo '{"error":{"text":'. $e->getMessage() .'}}';
        }

   } 

    public function dynamicchart(Request $request, Response $response, $args) {
        $response = $this->view->render($response, 'charts/dynamic-chart.phtml');
        return $response;
    }    


    public function dynamiccharts1(Request $request, Response $response, $args) {
        $response = $this->view->render($response, 'charts/dynamic-chart-s1.phtml');
        return $response;
    }    
    
    public function test_json_bigasdfasd(Request $request, Response $response, $args) {
        $sql = "SELECT * from udoo_data";
        
        try {
            $sth = $this->db->query($sql);
            $result = $sth->fetchAll();

            if ($result) {
                foreach (array("s1"=>'O2', "s2"=>'N', "s3"=>'PM', "s4"=>'Temperature', "s5"=>'SO2', "s6"=>'XYZ') as $sensor=>$sensor_label) {
                    // build array for Column labels
                    $json_array['cols'] = array(
                            array('id'=>'', 'label'=>'date/time', 'type'=>'string'),
                            array('id'=>'', 'label'=>$sensor_label, 'type'=>'number'));

                    // loop thru the sensor data and build sensor_array
                    foreach ($result as $row) {
                        $sensor_array = array();
                        $sensor_array[] = array('v'=>$row['datetime']);
                        $sensor_array[] = array('v'=>$row[$sensor]);
                    
                        // add current sensor_array line to $rows
                        $rows[] = array('c'=>$sensor_array);
                    }
                
                    // add $rows to $json_array
                    $json_array['rows'] = $rows;
                    $rows = array();
                
                    $master_array[$sensor][] = $json_array;
                }
                
              return $response->withHeader('Content-type', 'application/json')
              ->write(json_encode($master_array, JSON_NUMERIC_CHECK))
              ->withStatus(200);

            } else {
                $response = $response->withStatus(404);
            }
        } catch(PDOException $e) {
            echo '{"error":{"text":'. $e->getMessage() .'}}';
        }

   } 

    public function supercoffee(Request $request, Response $response, $args) {
        $response = $this->view->render($response, 'super-coffee.phtml');
        return $response;
    }    

    public function test_json(Request $request, Response $response, $args) {
        try {
            $chartdata = $this->charts->getUdooData();

            if ($chartdata) {
                // build array for Column labels
                $json_array['cols'] = array(
                        array('id'=>'', 'label'=>'date/time', 'type'=>'string'),
                        array('id'=>'', 'label'=>'CO2', 'type'=>'number'),
                        array('id'=>'', 'label'=>'SO2', 'type'=>'number'),
                        array('id'=>'', 'label'=>'NO2', 'type'=>'number'),
                        array('id'=>'', 'label'=>'temp', 'type'=>'number'),
                        array('id'=>'', 'label'=>'pm25', 'type'=>'number'));

                // loop thru the sensor data and build sensor_array
                foreach ($chartdata as $row) {
                    $sensor_array = array();
                    $sensor_array[] = array('v'=>$row['datetime']);
                    $sensor_array[] = array('v'=>$row['s1']);
                    $sensor_array[] = array('v'=>$row['s2']);
                    $sensor_array[] = array('v'=>$row['s3']);
                    $sensor_array[] = array('v'=>$row['s4']);
                    $sensor_array[] = array('v'=>$row['s5']);
                   
                    // add current sensor_array line to $rows
                    $rows[] = array('c'=>$sensor_array);
                }
            
                // add $rows to $json_array
                $json_array['rows'] = $rows;
                
              return $response->withHeader('Content-type', 'application/json')
              ->write(json_encode($json_array, JSON_NUMERIC_CHECK))
              ->withStatus(200);
   
            } else {
                $response = $response->withStatus(404);
            }
        } catch(PDOException $e) {
            echo '{"error":{"text":'. $e->getMessage() .'}}';
        }   
     }




  public function web_historical_aq_chart_data(Request $request, Response $response, $args) {
        try {
            $user_data  = $request->getParsedBody();

            $chartdata = json_decode($this->sensor_db_model->get_historical_aq_data($user_data['date_start'],$user_data['date_end'], 
                                                                                    $user_data['usn'], $user_data['ssn']), true);
            

            if ($chartdata['result_code'] == 1) {
                foreach (array('CO', 'SO2', 'O3', 'NO2', 'PM2_5', 'temperature') as $sensor_label) {
                    // build array for Column labels
                    $json_array['cols'] = array(
                            array('id'=>'', 'label'=>'date/time', 'type'=>'string'),
                            array('id'=>'', 'label'=>$sensor_label, 'type'=>'number'));

                    // loop thru the sensor data and build sensor_array
                    foreach ($chartdata as $row) {
                        if(is_array($row)){
                            $sensor_array = array();
                            $sensor_array[] = array('v'=>$row['datetime_format']);
                            $sensor_array[] = array('v'=>$row[$sensor_label]);
                        
                            // add current sensor_array line to $rows
                            $rows[] = array('c'=>$sensor_array);
                        }
                    }
                
                    // add $rows to $json_array
                    $json_array['rows'] = $rows;
                    $rows = array();
                
                    $master_array[$sensor_label][] = $json_array;
                }
                
              return $response->withJson($master_array);

            } else {
                $json = array(
                    'result_code' => 2,
                    'message' => $chartdata['message']
                );
                return $response->withJson($json);
            }
        } catch(PDOException $e) {
            $json = array(
                'result_code' => 0,
                'message' => $e->getMessage()
            );
            return $response->withJson($json);
        }
    }
}
