<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="5">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
  <#if PlayerServices??>
  <#if PlayerServices.isAvailable() == false>
      <meta http-equiv="refresh" content="5; url=/game">
  </#if>
  </#if>
</head>

<body>
<div class="page">
  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <#if Error??>
        <div id="error" class="ERROR">${Error}</div>
    </#if>

	<#if PlayerServices??>
	<#list Players as player>
	  <#if PlayerServices.Id() != player.Id()>
	    <#if player.isAvailable()>
	        <form action="./game" method="POST">
                <input type="submit" name="opponent" value="${player.Id()}" />
            </form>
        </#if>
	  </#if>
	</#list>
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
