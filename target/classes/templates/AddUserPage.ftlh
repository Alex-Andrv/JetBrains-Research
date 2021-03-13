<#-- @ftlvariable name="addUserForm" type="ru.itmo.wp.domain.User" -->

<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form">
        <div class="header">Add User</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="addUser"/>
                <div class="field">
                    <div class="name">
                        <label for="name">Name</label>
                    </div>
                    <div class="value">
                        <input autofocus id="name" name="name" value="${addUserForm.name!}"/>
                    </div>
                    <@c.error "addUserForm.name"/>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="surname">Surname</label>
                    </div>
                    <div class="value">
                        <input autofocus id="surname" name="surname" value="${addUserForm.surname!}"/>
                    </div>
                    <@c.error "addUserForm.surname"/>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="patronymic">Patronymic</label>
                    </div>
                    <div class="value">
                        <input autofocus id="patronymic" name="patronymic" value="${addUserForm.patronymic!}"/>
                    </div>
                    <@c.error "addUserForm.patronymic"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
</@c.page>
