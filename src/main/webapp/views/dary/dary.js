/**
 * Created by LPT12013 on 2016/9/9.
 */

var app = angular.module('daryApp', []);

app.controller("daryCtrl",function ($scope,$http,httpRequest) {
    $scope.dis ={};
    $scope.dis.tabTitle = '新增分销商';
    httpRequest.get("http://localhost:8080/test-ssi/dismapp/list").success(function(data,status,headers,config){
        $scope.dismapps = data;
    });

    $scope.search = function () {
       httpRequest.get("http://localhost:8080/test-ssi/dismapp/list",$scope.dismapp).success(function(data,status,headers,config){
           $scope.dismapps = data;
       });
    };

    $scope.addDisRequest = function(){

        $http.post("http://localhost:8080/test-ssi/dismapp",$scope.addDis).success(function(data){
             search();
        }).error(function(data){
            alert('error');
        });

        // $http({
        //     method:'post',
        //     url:'http://localhost:8080/test-ssi/dismapp',
        //
        //     data:$scope.addDis
        // }).success(function(data,status,headers,config){
        //
        // });

    };

    $scope.showTab = function (tabType) {
        var type = tabType.toString()
         if(tabType != null && angular.equals(type,'update')){
             $scope.dis.tabTitle = '修改分销商';
         }else{
             $scope.dis.tabTitle = '新增分销商';
         }
         $('#myModal').modal('show');

    };
    
    $scope.hideTab = function(){
        $('#myModal').modal('hide');
    };



}).service("httpRequest",function($http){
    this.post = function(url,method,params){
        return $http({
            method:method,
            url:url,
            data:params
        });
    };
    this.get=function(url,params) {
        return $http({
            method: "GET",
            url: url,
            params: params
        });

    };



});