<g:set var="date" value="${new Date() - 1}" />

<prettytime:display date="${date}" /> <br/>
<prettytime:display date="${date}" capitalize="true"/> <br/>
<prettytime:display date="${date}" showTime="true"/> <br/>
<prettytime:display date="${date}" showTime="true" format="HH:mm:ss"/> <br/>
