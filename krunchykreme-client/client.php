#!/usr/bin/env php
<?php
use Thrift\Protocol\TBinaryProtocol;
use Thrift\Transport\TSocket;
use Thrift\Transport\TBufferedTransport;
use Thrift\Exception\TException;

define('DEFAULT_HOST', 'localhost');
define('DEFAULT_PORT', 9090); 

$appDir = __DIR__;
require_once $appDir . '/Vendor/autoload.php';
require_once $appDir . '/KrunchyKreme/KrunchyKreme.php';
require_once $appDir . '/KrunchyKreme/Types.php';


$options = getopt("h:p:");
$host = isset($options['h']) ? $options['h'] : DEFAULT_HOST;
$port = isset($options['p']) ? $options['p'] : DEFAULT_PORT;


echo "Connecting to KrunchyKreme server $host:$port\n";

try {
    $client = getClient($host, $port);
    getAndDisplayMenu($client);
    orderDoughnut($client, 1 , 1);
    orderDoughnut($client, 2, -1);
    orderDoughnut($client, 10, 1);
}
catch(\Exception $e) {
    echo "Error: " . $e->getMessage() . "\n";
    exit(2);
}


function getClient($host, $port)
{
    $t1 = microtime(true);

    $socket = new TSocket($host, $port);
    $socket->setSendTimeout(10000);
    $socket->setRecvTimeout(10000);
    $transport = new TBufferedTransport($socket, 1024, 1024);
    $transport->open();

    $protocol = new TBinaryProtocol($transport);
    $client =  new \KrunchyKreme\KrunchyKremeClient($protocol);
    
    $t2 = microtime(true);
    $timeTaken = round($t2 - $t1, 3);
    echo 'Time taken to connect: ' . $timeTaken . "\n";
    
    return $client;
}

function getAndDisplayMenu($client)
{
    $t1 = microtime(true);
    $menu = $client->getMenu();

    $displayMenu = function($menu) {
        echo "The KrunchyKreme Menu: \n";
        foreach($menu as $doughnut) {
           echo "Doughut id=" . $doughnut->id . ", name=" . $doughnut->name . "\n";
        }
    };

    $t2 = microtime(true);
    $timeTaken = round($t2 - $t1, 3);
    echo 'Time taken to fetch menu: ' . $timeTaken . "\n";


    $displayMenu($menu);
}

function orderDoughnut($client, $doughnutId, $quantity)
{
    $t1 = microtime(true);
    
    $success = $client->order($doughnutId, $quantity);
    $order = "$quantity X doughnut id $doughnutId";
    echo ($success ? "Successful order" : "Failed order") . ' for ' . $order . "\n";

    $t2 = microtime(true);
    $timeTaken = round($t2 - $t1, 3);
    echo 'Time taken to order:' . $timeTaken . "\n";

}

