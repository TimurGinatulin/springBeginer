angular.module('app', ['ui.grid','ui.grid.pagination']).controller('restIndexController', function ($scope, $http) {
    const contextPath = 'http://localhost:2021/market/api';

    $scope.fillTable = function (pageNumber,size) {
    pageNumber = pageNumber > 0?pageNumber - 1:0;
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
    $scope.fillTable();

     function ($scope, StudentService) {
            var paginationOptions = {
                pageNumber: 1,
                pageSize: 5,
            sort: null
            };

        StudentService.getStudents(
          paginationOptions.pageNumber,
          paginationOptions.pageSize).success(function(data){
            $scope.gridOptions.data = data.content;
            $scope.gridOptions.totalItems = data.totalElements;
          });

        $scope.gridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:false,
        useExternalPagination: true,
            columnDefs:
               { name: 'name' },
               { name: 'cost' }
            ],
            onRegisterApi: function(gridApi) {
               $scope.gridApi = gridApi;
               gridApi.pagination.on.paginationChanged(
                 $scope,
                 function (newPage, pageSize) {
                   paginationOptions.pageNumber = newPage;
                   paginationOptions.pageSize = pageSize;
                   StudentService.getStudents(newPage,pageSize)
                     .success(function(data){
                       $scope.gridOptions.data = data.content;
                       $scope.gridOptions.totalItems = data.totalElements;
                     });
                });
            }
        };
    }]);



});