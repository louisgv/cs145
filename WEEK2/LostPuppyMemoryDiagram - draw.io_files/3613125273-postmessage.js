var g,k=this,l=function(a,b){var c=a.split("."),d=k;c[0]in d||!d.execScript||d.execScript("var "+c[0]);for(var e;c.length&&(e=c.shift());)c.length||void 0===b?d=d[e]?d[e]:d[e]={}:d[e]=b},p=function(a){var b=typeof a;if("object"==b)if(a){if(a instanceof Array)return"array";if(a instanceof Object)return b;var c=Object.prototype.toString.call(a);if("[object Window]"==c)return"object";if("[object Array]"==c||"number"==typeof a.length&&"undefined"!=typeof a.splice&&"undefined"!=typeof a.propertyIsEnumerable&&
!a.propertyIsEnumerable("splice"))return"array";if("[object Function]"==c||"undefined"!=typeof a.call&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("call"))return"function"}else return"null";else if("function"==b&&"undefined"==typeof a.call)return"object";return b};Math.random();
var aa=function(a,b,c){return a.call.apply(a.bind,arguments)},ba=function(a,b,c){if(!a)throw Error();if(2<arguments.length){var d=Array.prototype.slice.call(arguments,2);return function(){var c=Array.prototype.slice.call(arguments);Array.prototype.unshift.apply(c,d);return a.apply(b,c)}}return function(){return a.apply(b,arguments)}},q=function(a,b,c){q=Function.prototype.bind&&-1!=Function.prototype.bind.toString().indexOf("native code")?aa:ba;return q.apply(null,arguments)},u=function(a,b){function c(){}
c.prototype=b.prototype;a.v=b.prototype;a.prototype=new c;a.u=function(a,c,f){for(var h=Array(arguments.length-2),m=2;m<arguments.length;m++)h[m-2]=arguments[m];return b.prototype[c].apply(a,h)}};Function.prototype.bind=Function.prototype.bind||function(a,b){if(1<arguments.length){var c=Array.prototype.slice.call(arguments,1);c.unshift(this,a);return q.apply(null,c)}return q(this,a)};var v=function(a){if(Error.captureStackTrace)Error.captureStackTrace(this,v);else{var b=Error().stack;b&&(this.stack=b)}a&&(this.message=String(a))};u(v,Error);var ca=function(a,b){for(var c=a.split("%s"),d="",e=Array.prototype.slice.call(arguments,1);e.length&&1<c.length;)d+=c.shift()+e.shift();return d+c.join("%s")},w=String.prototype.trim?function(a){return a.trim()}:function(a){return a.replace(/^[\s\xa0]+|[\s\xa0]+$/g,"")},x=function(a,b){return a<b?-1:a>b?1:0};Math.random();var y=function(a,b){b.unshift(a);v.call(this,ca.apply(null,b));b.shift()};u(y,v);var z=function(a,b,c){if(!a){var d="Assertion failed";if(b)var d=d+(": "+b),e=Array.prototype.slice.call(arguments,2);throw new y(""+d,e||[]);}};var A=Array.prototype,C=function(a){return A.concat.apply(A,arguments)},da=function(a){var b=a.length;if(0<b){for(var c=Array(b),d=0;d<b;d++)c[d]=a[d];return c}return[]};var D=function(a){var b=arguments.length;if(1==b&&"array"==p(arguments[0]))return D.apply(null,arguments[0]);for(var c={},d=0;d<b;d++)c[arguments[d]]=!0;return c};D("area base br col command embed hr img input keygen link meta param source track wbr".split(" "));D("action","cite","data","formaction","href","manifest","poster","src");D("embed","iframe","link","object","script","style","template");var E;t:{var F=k.navigator;if(F){var G=F.userAgent;if(G){E=G;break t}}E=""};var ea=-1!=E.indexOf("Opera")||-1!=E.indexOf("OPR"),H=-1!=E.indexOf("Trident")||-1!=E.indexOf("MSIE"),I=-1!=E.indexOf("Gecko")&&-1==E.toLowerCase().indexOf("webkit")&&!(-1!=E.indexOf("Trident")||-1!=E.indexOf("MSIE")),J=-1!=E.toLowerCase().indexOf("webkit"),fa=J&&-1!=E.indexOf("Mobile"),ga=function(){var a=k.document;return a?a.documentMode:void 0},ha=function(){var a="",b;if(ea&&k.opera)return a=k.opera.version,"function"==p(a)?a():a;I?b=/rv\:([^\);]+)(\)|;)/:H?b=/\b(?:MSIE|rv)[: ]([^\);]+)(\)|;)/:
J&&(b=/WebKit\/(\S+)/);b&&(a=(a=b.exec(E))?a[1]:"");return H&&(b=ga(),b>parseFloat(a))?String(b):a}(),ia={},ja=function(a){if(!ia[a]){for(var b=0,c=w(String(ha)).split("."),d=w(String(a)).split("."),e=Math.max(c.length,d.length),f=0;0==b&&f<e;f++){var h=c[f]||"",m=d[f]||"",r=RegExp("(\\d*)(\\D*)","g"),B=RegExp("(\\d*)(\\D*)","g");do{var t=r.exec(h)||["","",""],n=B.exec(m)||["","",""];if(0==t[0].length&&0==n[0].length)break;b=x(0==t[1].length?0:parseInt(t[1],10),0==n[1].length?0:parseInt(n[1],10))||
x(0==t[2].length,0==n[2].length)||x(t[2],n[2])}while(0==b)}ia[a]=0<=b}},ka=k.document,la=ka&&H?ga()||("CSS1Compat"==ka.compatMode?parseInt(ha,10):5):void 0;var K;if(!(K=!I&&!H)){var L;if(L=H)L=H&&9<=la;K=L}K||I&&ja("1.9.1");H&&ja("9");var M;M=!1;var N=E;N&&(-1!=N.indexOf("Firefox")||-1!=N.indexOf("Camino")||-1!=N.indexOf("iPad")||-1!=N.indexOf("iPhone")||-1!=N.indexOf("iPod")||-1!=N.indexOf("Chrome")||-1!=N.indexOf("Android")||-1!=N.indexOf("Safari")&&(M=!0));var ma=M;var oa=function(a){var b=window;if(fa&&ma&&a){a.focus();var c=0,d=null,d=a.setInterval(function(){b.closed||5==c?(a.clearInterval(d),na(b)):(b.close(),c++)},150)}else b.close(),na(b)},na=function(a){if(!a.closed&&a.document&&a.document.body)if(a=a.document.body,z(null!=a,"goog.dom.setTextContent expects a non-null value for node"),"textContent"in a)a.textContent="Please close this window.";else if(3==a.nodeType)a.data="Please close this window.";else if(a.firstChild&&3==a.firstChild.nodeType){for(;a.lastChild!=
a.firstChild;)a.removeChild(a.lastChild);a.firstChild.data="Please close this window."}else{for(var b;b=a.firstChild;)a.removeChild(b);z(a,"Node cannot be null or undefined.");a.appendChild((9==a.nodeType?a:a.ownerDocument||a.document).createTextNode("Please close this window."))}};var pa="StopIteration"in k?k.StopIteration:Error("StopIteration"),O=function(){};O.prototype.next=function(){throw pa;};O.prototype.s=function(){return this};var P=function(a,b){this.g={};this.c=[];this.m=this.b=0;var c=arguments.length;if(1<c){if(c%2)throw Error("Uneven number of arguments");for(var d=0;d<c;d+=2)this.set(arguments[d],arguments[d+1])}else if(a){var e;if(a instanceof P)e=a.j(),d=a.h();else{var c=[],f=0;for(e in a)c[f++]=e;e=c;c=[];f=0;for(d in a)c[f++]=a[d];d=c}for(c=0;c<e.length;c++)this.set(e[c],d[c])}};P.prototype.h=function(){Q(this);for(var a=[],b=0;b<this.c.length;b++)a.push(this.g[this.c[b]]);return a};
P.prototype.j=function(){Q(this);return this.c.concat()};P.prototype.k=function(a){return R(this.g,a)};P.prototype.remove=function(a){return R(this.g,a)?(delete this.g[a],this.b--,this.m++,this.c.length>2*this.b&&Q(this),!0):!1};var Q=function(a){if(a.b!=a.c.length){for(var b=0,c=0;b<a.c.length;){var d=a.c[b];R(a.g,d)&&(a.c[c++]=d);b++}a.c.length=c}if(a.b!=a.c.length){for(var e={},c=b=0;b<a.c.length;)d=a.c[b],R(e,d)||(a.c[c++]=d,e[d]=1),b++;a.c.length=c}};g=P.prototype;
g.get=function(a,b){return R(this.g,a)?this.g[a]:b};g.set=function(a,b){R(this.g,a)||(this.b++,this.c.push(a),this.m++);this.g[a]=b};g.forEach=function(a,b){for(var c=this.j(),d=0;d<c.length;d++){var e=c[d],f=this.get(e);a.call(b,f,e,this)}};g.clone=function(){return new P(this)};
g.s=function(a){Q(this);var b=0,c=this.c,d=this.g,e=this.m,f=this,h=new O;h.next=function(){for(;;){if(e!=f.m)throw Error("The map has changed since the iterator was created");if(b>=c.length)throw pa;var h=c[b++];return a?h:d[h]}};return h};var R=function(a,b){return Object.prototype.hasOwnProperty.call(a,b)};var qa=/^(?:([^:/?#.]+):)?(?:\/\/(?:([^/?#]*)@)?([^/#?]*?)(?::([0-9]+))?(?=[/#?]|$))?([^?#]+)?(?:\?([^#]*))?(?:#(.*))?$/,ra=function(a){if(S){S=!1;var b=k.location;if(b){var c=b.href;if(c&&(c=(c=ra(c)[3]||null)?decodeURI(c):c)&&c!=b.hostname)throw S=!0,Error();}}return a.match(qa)},S=J;var T=function(a,b){var c;if(a instanceof T)this.d=void 0!==b?b:a.d,sa(this,a.i),c=a.q,U(this),this.q=c,c=a.l,U(this),this.l=c,ta(this,a.p),c=a.o,U(this),this.o=c,ua(this,a.f.clone()),c=a.n,U(this),this.n=c;else if(a&&(c=ra(String(a)))){this.d=!!b;sa(this,c[1]||"",!0);var d=c[2]||"";U(this);this.q=V(d);d=c[3]||"";U(this);this.l=V(d,!0);ta(this,c[4]);d=c[5]||"";U(this);this.o=V(d,!0);ua(this,c[6]||"",!0);c=c[7]||"";U(this);this.n=V(c)}else this.d=!!b,this.f=new W(null,0,this.d)};g=T.prototype;
g.i="";g.q="";g.l="";g.p=null;g.o="";g.n="";g.t=!1;g.d=!1;g.toString=function(){var a=[],b=this.i;b&&a.push(X(b,va,!0),":");if(b=this.l){a.push("//");var c=this.q;c&&a.push(X(c,va,!0),"@");a.push(encodeURIComponent(String(b)).replace(/%25([0-9a-fA-F]{2})/g,"%$1"));b=this.p;null!=b&&a.push(":",String(b))}if(b=this.o)this.l&&"/"!=b.charAt(0)&&a.push("/"),a.push(X(b,"/"==b.charAt(0)?wa:xa,!0));(b=this.f.toString())&&a.push("?",b);(b=this.n)&&a.push("#",X(b,ya));return a.join("")};g.clone=function(){return new T(this)};
var sa=function(a,b,c){U(a);a.i=c?V(b,!0):b;a.i&&(a.i=a.i.replace(/:$/,""))},ta=function(a,b){U(a);if(b){b=Number(b);if(isNaN(b)||0>b)throw Error("Bad port number "+b);a.p=b}else a.p=null},ua=function(a,b,c){U(a);b instanceof W?(a.f=b,a.f.r(a.d)):(c||(b=X(b,za)),a.f=new W(b,0,a.d))},U=function(a){if(a.t)throw Error("Tried to modify a read-only Uri");};T.prototype.r=function(a){this.d=a;this.f&&this.f.r(a);return this};
var V=function(a,b){return a?b?decodeURI(a):decodeURIComponent(a):""},X=function(a,b,c){return"string"==typeof a?(a=encodeURI(a).replace(b,Aa),c&&(a=a.replace(/%25([0-9a-fA-F]{2})/g,"%$1")),a):null},Aa=function(a){a=a.charCodeAt(0);return"%"+(a>>4&15).toString(16)+(a&15).toString(16)},va=/[#\/\?@]/g,xa=/[\#\?:]/g,wa=/[\#\?]/g,za=/[\#\?@]/g,ya=/#/g,W=function(a,b,c){this.e=a||null;this.d=!!c},Y=function(a){if(!a.a&&(a.a=new P,a.b=0,a.e)){var b=q(a.add,a);a=a.e.split("&");for(var c=0;c<a.length;c++){var d=
a[c].indexOf("="),e=null,f=null;0<=d?(e=a[c].substring(0,d),f=a[c].substring(d+1)):e=a[c];b(decodeURIComponent(e.replace(/\+/g," ")),f?decodeURIComponent(f.replace(/\+/g," ")):"")}}};g=W.prototype;g.a=null;g.b=null;g.add=function(a,b){Y(this);this.e=null;a=Z(this,a);var c=this.a.get(a);c||this.a.set(a,c=[]);c.push(b);this.b++;return this};g.remove=function(a){Y(this);a=Z(this,a);return this.a.k(a)?(this.e=null,this.b-=this.a.get(a).length,this.a.remove(a)):!1};
g.k=function(a){Y(this);a=Z(this,a);return this.a.k(a)};g.j=function(){Y(this);for(var a=this.a.h(),b=this.a.j(),c=[],d=0;d<b.length;d++)for(var e=a[d],f=0;f<e.length;f++)c.push(b[d]);return c};g.h=function(a){Y(this);var b=[];if("string"==typeof a)this.k(a)&&(b=C(b,this.a.get(Z(this,a))));else{a=this.a.h();for(var c=0;c<a.length;c++)b=C(b,a[c])}return b};g.set=function(a,b){Y(this);this.e=null;a=Z(this,a);this.k(a)&&(this.b-=this.a.get(a).length);this.a.set(a,[b]);this.b++;return this};
g.get=function(a,b){var c=a?this.h(a):[];return 0<c.length?String(c[0]):b};g.toString=function(){if(this.e)return this.e;if(!this.a)return"";for(var a=[],b=this.a.j(),c=0;c<b.length;c++)for(var d=b[c],e=encodeURIComponent(String(d)),d=this.h(d),f=0;f<d.length;f++){var h=e;""!==d[f]&&(h+="="+encodeURIComponent(String(d[f])));a.push(h)}return this.e=a.join("&")};g.clone=function(){var a=new W;a.e=this.e;this.a&&(a.a=this.a.clone(),a.b=this.b);return a};
var Z=function(a,b){var c=String(b);a.d&&(c=c.toLowerCase());return c};W.prototype.r=function(a){a&&!this.d&&(Y(this),this.e=null,this.a.forEach(function(a,c){var d=c.toLowerCase();c!=d&&(this.remove(c),this.remove(d),0<a.length&&(this.e=null,this.a.set(Z(this,d),da(a)),this.b+=a.length))},this));this.d=a};var Ba=function(a){a=new T(a);var b="&"+window.name;U(a);a.f.set(b,!0);b=a.f.h("parent");U(a);a.f.remove("parent");1==b.length&&(b=gadgets.rpc.getOrigin(String(b[0])),U(a),a.f.set("parent",b));b="&"+window.name;U(a);a.f.remove(b);return a.toString()},Ca=function(a,b,c,d,e,f,h){if(!d||!d.document.domain)return!1;var m=Ba(String(d.document.location.href));if(m.substr(0,c.length)!=c)return!1;c=gadgets.util.getUrlParameters(m);if(!b||!c.parent||b!=gadgets.rpc.getOrigin(String(c.parent)))return!1;if(!e)return l("oauth2callbackUrl",
a),d.oauth2verify.call(d,String(window.name),h)?!0:!1;d.oauth2callback.call(d,a);try{f()}catch(r){}return!0},Da=function(){try{return window.parent!=window}catch(a){}return!0},Ea=function(){try{return!!window.opener}catch(a){}return!0},Fa=function(a,b,c,d,e){try{var f=Da(),h=!f&&Ea(),m=!0,r=null,B=function(){m&&oa(r)};if(!f&&!h)return;var m=(h||!f)&&"keep_open"!==e,r=h?window.opener:window.parent,t=Ba(b);try{var n;if(d&&(n=r.frames[d],Ca(a,c,t,n,!h,B,e)))return;for(b=0;b<r.frames.length;++b)if(n=
r.frames[b],Ca(a,c,t,n,!h,B,e)){m=!1;break}}catch(Ga){}}catch(Ha){}B()};
l("postmessage.onLoad",function(){window.name="pmh"+String(2147483647*shindig.random()|0);var a=document.createElement("div"),b="true"==document.getElementById("error").value,c=gadgets.rpc.getOrigin(document.getElementById("origin").value),b=c+(b?"?":"#")+document.getElementById("response-form-encoded").value,d=document.getElementById("relay-endpoint").value,e=null,f=document.getElementById("proxy");f&&f.value&&(e=f.value);var f=document.getElementById("after-redirect"),h=null;f&&f.value&&(h=f.value);
a.appendChild(document.createTextNode(b));a.setAttribute("id","postmessage-hello");Fa(b,d,c,e,h)});l("postmessage.closePopup",function(){var a=null;try{var b=Da(),c=!b&&Ea();if(!b&&!c)return;a=c?window.opener:window.parent}catch(d){}oa(a)});
