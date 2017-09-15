angular.module('admin', [])
    .controller('adminuser', function($scope, $http) {

        $scope.userdata = {};

        $http.get('/api/users')
            .then(function(data){
                $scope.userdata = data.data;
            });
    });

angular.module('password', [])
    .controller('user', function($scope, $http) {

        $scope.changePass = function(){
            $http.post("/api/users/set_password?password=" + $scope.password ).then(function(data){console.log(data)});
        }


    });