<div class="page-header">
    <h1>Users</h1>
</div>
<div class="row">

    <div ng-controller="userCtrl" class="col-md-12">
        <form novalidate>
            First Name:
            <input type="text" ng-model="userToAdd.firstName">
            <br>

            Last Name:
            <input type="text" ng-model="userToAdd.lastName">
            <br>

            Middle Name:
            <input type="text" ng-model="userToAdd.middleName">
            <br>

            Birth Date:
            <input type="date" ng-model="userToAdd.birthDate">
            <br>

            Email:
            <input type="text" ng-model="userToAdd.email">
            <br>

            Address:
            <div class="col-md-12">
                City:
                <input type="text" ng-model="userToAdd.address.city">
                <br>

                Street:
                <input type="text" ng-model="userToAdd.address.street">
                <br>

                House number:
                <input type="text" ng-model="userToAdd.address.house">
                <br>

                Apartment number:
                <input type="text" ng-model="userToAdd.address.apartment">
                <br>
            </div>


            <button ng-click="reset()">RESET</button>
        </form>
        <p>form = {{ userToAdd }}</p>
    </div>

</div>