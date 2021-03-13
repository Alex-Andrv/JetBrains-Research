<#-- @ftlvariable name="editBookForm" type="ru.itmo.wp.domain.Book" -->

<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form">
        <div class="header">Edit Book</div>
        <div class="body">
            <#if book??>
                <form method="post" class="editCipherForm">
                    <input type="submit" name="action" value="editCipher">
                    <input id="cipher" name="cipher" value=""/>
                </form>
                <#if book.user??>
                    <form method="post"  class="pickUpBookForm">
                        <input type="submit" name="action" value="pickUpBook" onclick="pickUpBook(this)">
                    </form>
                <#else>
                    <form method="post" class="giveOutBookForm">
                        <input type="submit" name="action" value="giveOutBook">
                        <input id="userId" name="userId" value=""/>
                    </form>
                    <form method="post" class="deleteBookForm">
                        <input type="submit" name="action" value="deleteBook" onclick="deleteBook(this)">
                    </form>
                </#if>
            <#else>
                <form method="post" action="">
                    <input type="hidden" name="action" value="editBook"/>
                    <div class="field">
                        <div class="name">
                            <label for="cipher">Cipher</label>
                        </div>
                        <div class="value">
                            <input autofocus id="cipher" name="cipher" value="${editBookForm.cipher!}"/>
                        </div>
                        <@c.error "editBookForm.cipher"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="find">
                    </div>
                </form>
            </#if>
        </div>
    </div>
    <script>
        $(function () {
            $("form.editCipherForm").submit(function () {
                alert("editCipherForm");
                return false;
            });
        }),
        $(function () {
            $("form.giveOutBookForm").submit(function () {
                alert("giveOutBookForm");
                return false;
            });
        }),
        $(function () {
            $("form.pickUpBookForm").submit(function () {
                ajax({
                    action: "enter",
                    login: login,
                    password: password
                }, function (response) {
                    if (response["error"]) {
                        $error.text(response["error"]);
                    }
                });
                return false;
            });
        }),
        $(function () {
            $("form.deleteBookForm").submit(function () {
                alert("ok");
                ajax({
                    method: "POST",
                    dataType: "json",
                    url:  "editBook/deleteBookForm"
                }, function (response) {
                });
                return false;
            });
        })
    </script>
</@c.page>
