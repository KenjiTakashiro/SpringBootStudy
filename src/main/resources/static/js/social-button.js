
/*--------------------------------------------------------------------------
	
	Script Name : Social Button Script
	Author : FIRSTSTEP - Motohiro Tani
	Author URL : https://www.1-firststep.com
	Create Date : 2015/11/24
	Version : 2.1
	Last Update : 2019/05/03
	
--------------------------------------------------------------------------*/


function social_button() {
	
	var url   = encodeURI( location.href );
	var title = encodeURI( document.title );
	
	
	var div_social = document.getElementById( 'social' );
	
	
	var social_ul       = document.createElement( 'ul' );
	social_ul.className = 'social-button';
	div_social.appendChild( social_ul );
	
	
	var twitter_li       = document.createElement( 'li' );
	twitter_li.className = 'twitter';
	twitter_li.innerHTML = '<a href="//twitter.com/share?text=' +title+ '&url=' +url+ '" target="_blank" rel="nofollow">Twitter</a>';
	social_ul.appendChild( twitter_li );
	
	
	var facebook_li       = document.createElement( 'li' );
	facebook_li.className = 'facebook';
	facebook_li.innerHTML = '<a href="//www.facebook.com/sharer.php?t=' +title+ '&u=' +url+ '" target="_blank" rel="nofollow">facebook</a>';
	social_ul.appendChild( facebook_li );
	
	
	var hatena_li       = document.createElement( 'li' );
	hatena_li.className = 'hatena';
	hatena_li.innerHTML = '<a href="//b.hatena.ne.jp/add?mode=confirm&title=' +title+ '&url=' +url+ '" target="_blank" rel="nofollow">はてなブックマーク</a>';
	social_ul.appendChild( hatena_li );
	
	
	var line_li       = document.createElement( 'li' );
	line_li.className = 'line';
	line_li.innerHTML = '<a href="//line.me/R/msg/text/?' +title+ '%0D%0A' +url+ '" target="_blank" rel="nofollow">LINE</a>';
	social_ul.appendChild( line_li );
	
}




document.addEventListener( 'DOMContentLoaded', function() {
	social_button();
}, false );