angular.module('admin', [])
    .controller('adminuser', function ($scope, $http) {

        $scope.userdata = {};

        $http.get('/api/users')
            .then(function (data) {
                $scope.userdata = data.data;
            });
    })
    .controller('adminadd', function ($scope, $http) {


        $scope.createUser = function(){
            console.log("Hello", $scope.user );
            $http.post('/api/users/create', $scope.user);
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
        }

    });