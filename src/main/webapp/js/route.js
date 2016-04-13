app.config(function ($stateProvider) {
    $stateProvider
        .state('home', {
            url: '/'
        })


        // USERS
        .state('users', {
            url: '/users?page&pageSize',
            templateUrl: 'partials/users.html',
            controller: usersController,
            params: {
                page: '0',
                pageSize: '5'
            },
            data: {
                permitTo: ['ROLE_SYSTEM_ADMIN']
            }
        })

        .state('userDetails', {
            url: '/users/{userId:int}',
            templateUrl: 'partials/userDetails.html',
            controller: userDetailsController
        })

        .state('addUser', {
            url: '/users/add',
            templateUrl: 'partials/userDetails.html',
            controller: addUserController
        })


        // COMPANIES
        .state('companies', {
            url: '/companies?companyType&page&pageSize',
            templateUrl: 'partials/companies.html',
            controller: companiesController,
            params: {
                companyType: '',
                page: '0',
                pageSize: '5'
            }
        })

        .state('companyDetails', {
            url: '/companies/{companyId:int}',
            templateUrl: 'partials/companyDetails.html',
            controller: companyDetailsController
        })

        .state('addCompany', {
            url: '/companies/add',
            templateUrl: 'partials/companyDetails.html',
            controller: addCompanyController
        });
});