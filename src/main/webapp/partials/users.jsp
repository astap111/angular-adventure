<div class="page-header">
    <h1>Users</h1>
</div>
<div class="row">

    <div class="col-md-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Full Name</th>
                <th>Login</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="user in users">
                <td>
                    <a ui-sref="userDetails({userId: user.id})">{{ user.firstName + ' ' + user.lastName }}</a>
                </td>
                <td>
                    {{ user.login }}
                </td>
                <td>
                    {{ user.role.roleName }}
                </td>
            </tbody>
        </table>
        <button ui-sref="addUser" class="btn btn-default">Add user</button>
    </div>

</div>