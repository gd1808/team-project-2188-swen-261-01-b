<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

	<#if PlayerServices??>
	<#list Players as player>
	  <#if PlayerServices.Id() != player.Id()>
	    <form action="./game" method="POST">
            <button type="submit" name="opponent" value="${player.Id()}">${player.Id()}</button>
        </form>
	  </#if>
	</#list>
	<!-- TODO: provide list of players to start a game with -->
	<#else>
	  <#if totalPlayers??>
	  <p>
	    Total players online: ${totalPlayers}
	  </p>
	  </#if>
	</#if>
    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
