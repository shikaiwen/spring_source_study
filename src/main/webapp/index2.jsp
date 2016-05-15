
 <a href='javascript:$("#desc").css("display",  $("#desc").is(":visible")?"none":"block" );'> </a> 
<div id="desc">
<h2>说明 </h2>
<p>本插件是对Freemarker数据的展现，为了使数据清晰过滤了一些Freemaker内部属性
这里一共显示了四个对象，是Freemarker对Servlet环境对象的封装，对应关系如下
</p>
<p>1、HttpRequestHashModel中的数据是HttpServletRequest的setAttribute的数据，SpringMVC的ModelAndView数据也在这个对象里</p>
<p>2、HttpRequestParameterHashModel是通过Get请求的参数，和HttpServletRequest的getParameter()获取数据方法一样</p>
<p>3、HttpSessionHashModel对应着HttpSession</p>
<p>4、ServletContextHashModel对应着ServletContext</p>
</div>

<@kdebug showFolder=true/>
   

