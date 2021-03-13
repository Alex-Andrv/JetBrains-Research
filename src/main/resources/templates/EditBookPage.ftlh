<#-- @ftlvariable name="editBookForm" type="ru.itmo.wp.domain.Book" -->

<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form">
        <div class="header">Edit Book</div>
        <div class="body">
            <#if book??>
                <div class="editCipher">
                    <input type="submit" name="action" value="editCipher">
                    <input id="cipher" name="cipher" value=""/>
                </div>
                    <#if book.user??>
                         <input type="submit" name="action" value="pickUpBook" onclick="pickUpBook(this)">
                    <#else>
                        <div class="giveOutBook">
                            <input type="submit" name="action" value="giveOutBook">
                            <input id="userId" name="userId" value=""/>
                        </div>
                         <input type="submit" name="action" value="deleteBook" onclick="deleteBook(this)">
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
            $(".enter form").submit(function () {
                const login = $(this).find("input[name='login']").val();
                const password = $(this).find("input[name='password']").val();
                const $error = $(this).find(".error");

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
        })
    </script>
</@c.page>
