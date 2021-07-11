angular.module('app', []).controller('restIndexController', function ($scope, $http) {
    const contextPath = 'http://localhost:2021/market/api';
    var invocation = new XMLHttpRequest();
    var url = 'http://localhost:2021/market';
    var body = '<?xml version="1.0"?><person><name>Arun</name></person>';

    $scope.fillTable = function () {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                title:$scope.filter ? $scope.filter.name : null
            }
        }).then(function (response) {
        console.log(response)
            $scope.ProductsList = response.data;
        });
    };
    $scope.deleteProduct = function(id){
    callOtherDomain();
    $http({
       url: contextPath + "/" +id,
       method: 'DELETE',
       mozSystem: true
    }).then(function(response){
    alert("Deleted");
    $scope.fillTable();
    });
    };
    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath , $scope.newProduct)
            .then(function (response) {
             alert("Added");
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };
function callOtherDomain(){
  if(invocation)
    {
      invocation.open('POST', url, true);
      invocation.setRequestHeader('X-PINGOTHER', 'pingpong');
      invocation.setRequestHeader('Content-Type', 'application/xml');
      invocation.send(body);
    }
}

    $scope.fillTable();
});