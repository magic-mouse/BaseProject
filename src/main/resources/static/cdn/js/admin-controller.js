angular.module('admin', [])
    .controller('adminuser', function ($scope, $http) {

        $scope.userdata = {};

        $http.get('/api/users')
            .then(function (data) {
                $scope.userdata = data.data;
            });
    })
    .controller('adminadd', function ($scope, $http, $window) {
        $scope.createUser = function(){

            $http.post('/api/users/create', $scope.user).then(
                function(data){
                    $window.location.href = '/admin/user/' + data.data.userName;
                },
                function (error){
                    console.log(error);
                }
            );
        }


});

angular.module('password', [])
    .controller('user', function ($scope, $http) {

        $scope.changePass = function () {
            if ($scope.verify_pass != $scope.password) {
                $scope.error = "Passwords did not match";
                return false;
            }
            $http.post("/api/users/set_password?oldpassword=" + $scope.old_pass + "&password=" + $scope.password).then(function (data) {
                $scope.error = null;
                $scope.success = "Password updated";
            }, function (response) {
                console.log(response, response.data, response.status, response.statusText);
                $scope.success = null;
                $scope.error = "something went wrong, please review your input and try again";
            });
        }


    });

angular.module('base', [])
    .controller('baseadd', function ($scope, $http) {

        $scope.createBase = function(){
            console.log($scope.page);
            $http.post("/api/base/add", $scope.page).then(function (data){
                console.log(data);
                $scope.error = null;
                $scope.success = "";
            }, function(response){
                console.log(response, response.data, response.status, response.statusText);
                $scope.success = null;
                $scope.error = "something went wrong, please review your input and try again";
            });
        }

    }) .controller('baseadmin', function ($scope, $http) {

        $scope.pagesData = {};

        $http.get('/api/base').then(function (data) {
            $scope.pagesData = data.data;
            console.log($scope.pagesData);
        });
    });

angular.module('menu', [])
 .controller('menuadmin', function ($scope, $http) {

    $scope.menuData = {};

    $http.get('/api/menu').then(function (data) {
        $scope.menuData = data.data;
        console.log($scope.menuData);
    });
});