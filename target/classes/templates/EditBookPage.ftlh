<#-- @ftlvariable name="editBookForm" type="ru.itmo.wp.domain.Book" -->

<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form">
        <div class="header">Edit Book</div>
        <div class="body">
            <#if book??>
                <form onsubmit="editCipher(this); return false">
                    <input type="submit" name="action" value="editCipher">
                    <input id="cipher" name="cipher" value=""/>
                </form>
                    <#if book.user??>
                         <input type="submit" name="action" value="pickUpBook" onclick="pickUpBook(this)">
                    <#else>
                        <form onsubmit="giveOutBook(this); return false">
                            <input type="submit" name="action" value="giveOutBook">
                            <input id="userId" name="userId" value=""/>
                        </form>
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
    <script type="text/javascript">
        function helpSend(url, json) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST",  location.href + url, true);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);
        }
        
        function editCipher(form) {
            alert(form);
            return false;
          //  helpSend("/editCipher", JSON.stringify({cipher: }));
        }
        function pickUpBook(button) {
            helpSend("/pickUpBook", JSON.stringify({}));
        }
        function giveOutBook(form) {
            console.log(this);
            return false;
        }
        function deleteBook(button) {
            helpSend("/deleteBook", JSON.stringify({}));
        }

    </script>
</@c.page>
