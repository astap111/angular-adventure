function wrapEntityWithDates(entity, fieldNames) {
    for (var i = 0; i < fieldNames.length; i++) {
        entity[fieldNames[i]] = new Date(entity[fieldNames[i]]);
    }

}

function wrapConsignmentDate(consignment) {
    //wrap 3 dates
}

function getUserRoles() {
    return [{roleName: 'ROLE_SYSTEM_ADMIN'}, {roleName: 'ROLE_ADMIN'}, {roleName: 'ROLE_DISPATCHER'}, {roleName: 'ROLE_MANAGER'}, {roleName: 'ROLE_CONTROLLER'}, {roleName: 'ROLE_OWNER'}];
}

function getStatuses() {
    return ['ACTIVE', 'BLOCKED'];
}