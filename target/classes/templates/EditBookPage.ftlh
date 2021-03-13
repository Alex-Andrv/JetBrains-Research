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
                        <input type="submit" name="action" value="pickUpBook">
                    </form>
                <#else>
                    <form method="post" class="giveOutBookForm">
                        <input type="submit" name="action" value="giveOutBook">
                        <input id="userId" name="userId" value=""/>
                    </form>
                    <form method="post" class="deleteBookForm">
                        <input type="submit" name="action" value="deleteBook">
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
                const cipher = $(this).find("input[name='cipher']").val();
                $.ajax({
                    dataType: "json",
                    url: "/editBook/editCipher",
                    data: {
                        cipher: cipher
                    },
                    type: "POST"
                }).done( document.location.href = "http://localhost:8080/");
                return false;
            });
        }),
        $(function () {
            $("form.giveOutBookForm").submit(function () {
                const userId = $(this).find("input[name='userId']").val();
                $.ajax({
                    dataType: "json",
                    url: "/editBook/giveOutBook",
                    data: {
                        userId: userId
                    },
                    type: "POST",
                }).done( document.location.href = "http://localhost:8080/");
                return false;
            });
        }),
        $(function () {
            $("form.pickUpBookForm").submit(function () {
                $.ajax({
                    dataType: "json",
                    url: "/editBook/pickUpBook",
                    data: {
                    },
                    type: "POST"
                }).done( document.location.href = "http://localhost:8080/");
                return false;
            });
        }),
        $(function () {
            $("form.deleteBookForm").submit(function () {
                $.ajax({
                    dataType: "json",
                    url: "/editBook/deleteBook",
                    data: {
                    },
                    type: "POST"
                }).done( document.location.href = "http://localhost:8080/");
                return false;
            });
        })
    </script>
</@c.page>
