<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="3">
    <title>Web Checkers | ${title}</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <#if PlayerServices??>
        <#if PlayerServices.isAvailable() == false>
            <meta http-equiv="refresh" content="0; url=/game">
        </#if>
    </#if>
</head>

<body>
<div class="page">
    <h1>Web Checkers | ${title}</h1>

    <#-- Provide a navigation bar -->
    <#include "nav-bar.ftl" />

    <div class="body">

        <#if Error??>
            <div id="error" class="ERROR">${Error}</div>
        </#if>

        <#if PlayerServices??>
            <#list Players as player>
                <#if PlayerServices.Id() != player.Id()>
                    <table>
                        <tbody>
                        <#if player.isAvailable()>
                            <form action="./game" method="POST">
                                <tr>
                                    <td><input type="submit" name="opponent" value="${player.Id()}" /></td>
                                    <td><strong style="padding-left: 2em">Available</strong></td>
                                </tr>
                            </form>
                        <#else >
                            <tr>
                                <td>${player.Id()}</td>
                                <td><strong style="padding-left: 2em">Busy</strong></td>
                            </tr>
                        </#if>
                        </tbody>
                    </table>
                </#if>
            </#list>
        <#else>
            <#if totalPlayers??>
                <p>
                    Total players online: ${totalPlayers}
                </p>
            </#if>
        </#if>

        <#if PlayerServices??>
            <hr />
            <p>Saved Games</p>
            <#list savedGames as game>
                <table>
                    <tbody>
                        <form action = "./game" method="POST">
                            <tr>
                                <td><input type="submit" name="game" value="${"placeholder"}" /></td>
                                <td><strong style = "padding-left: 2em">Replay</strong></td>
                            </tr>
                        </form>
                    </tbody>
                </table>
            </#list>
        </#if>
        <#-- TODO: future content on the Home:
                to start games,
                spectating active games,
                or replay archived games
        -->

    </div>

</div>
</body>

</html>
