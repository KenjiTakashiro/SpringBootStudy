//ajax通信のjQuery　改変の必要性あり
$(function(){
    var setArea = $('#loadarea'),
    loadNum = 5, // 読み込む個数
    loadTxt = 'Now Loading...', // Loading中の表示テキスト
    btnTxt = 'もっと見る', // 	ボタンテキスト
    fadeSpeed = 500; // フェードスピード

    setArea.after('<div id="btnMore">' + btnTxt + '</div>');	// 	変数setAreaにブロック要素を追加
    var setMore = setArea.next('#btnMore');	// setArea以降の'#btnMore'要素を取得

    //	カウント回数を設定
	$(window).on('load', function() {
		var cnt = 0;
	});

    setMore.click(function(){
    	//	カウント更新
    	cnt ++;	
    	//	クリック回数の送信テスト
    	$.ajax({
    		url:'',
    		method:'post',
    		data:{id:'count',num:cnt}
    		timeout:100000
    	}).done(function(data,status,xhr){
    		$('#result').html(data);
    	}).fail(function(xhr,status,error){
    		$('#log').append('xhr.status = ' + xhr.status + '<br>');
    		$('#log').append('xhr.statusText = ' + xhr.statusText + '<br>');
    		$('#log').append('status = ' + xhr.status + '<br>');
    		$('#log').append('error = ' + error + '<br>');
    	});
    	
        $.ajax({
            url: 'js/include.json',	// 	送信先
            dataType: 'json',		// 	データ型
            success : function(data){
                var dataLengh = data.length,	//	返されたデータの長さを宣言
                loadItemLength = setArea.find('.loadItem').length,	// setArea内のloadItemクラスの長さを宣言
                setAdj = (dataLengh)-(loadItemLength),	// 返されたデータの長さ-ロードするデータの長さ
                setBeg = (dataLengh)-(setAdj);	// 返されたデータの長さ-（返されたデータ長-ロードデータ長）
                if(!(dataLengh == loadItemLength)){	// 返されたjsonがロードするデータの長さが等しい場合
                    setArea.append('<div id="nowLoading">' + loadTxt + '</div>'); 	// setAreaのあとに「now
																					// loading...」を追加
                    if(loadItemLength == 0){	//	ロード先のデータの長さが0（≒初回ロード時）
                        if(dataLengh <= loadNum){	//	取得したjson < ロード数上限
                            for (var i=0; i<dataLengh; i++) {
                                $('<div id="item' + data[i].itemNum + '" class="loadItem">' + data[i].itemSource + '</div>').appendTo(setArea).css({opacity:'0'}).animate({opacity:'1'},fadeSpeed);	// 要素を追加する
                            }
                            setMore.remove();	// moreボタンを削除
                        } else {	//	取得したjson > ロード数上限
                            for (var i=0; i<loadNum; i++) {	// 	素を追加
                                $('<div id="item' + data[i].itemNum + '" class="loadItem">' + data[i].itemSource + '</div>').appendTo(setArea).css({opacity:'0'}).animate({opacity:'1'},fadeSpeed);	
                            }
                        }
                    } else if(loadItemLength > 0 && loadItemLength < dataLengh){	// ２回目以降の読込＆読み込み数>json長の場合
                        if(loadNum < setAdj){	// 	読み込み予定数＜(json長)-(loadItemLength)
                            for (var i=0; i<loadNum; i++) {	//	読み込み予定数だけ繰り返し
                                v = i+setBeg;	//	？？
                                $('<div id="item' + data[v].itemNum + '" class="loadItem">' + data[v].itemSource + '</div>').appendTo(setArea).css({opacity:'0'}).animate({opacity:'1'},fadeSpeed);
                            }
                        } else if(loadNum >= setAdj){	// 	読み込み予定数＞＝(json長)-(loadItemLength)　
                            for (var i=0; i<setAdj; i++) {	//	 返されたデータの長さ-ロードするデータの長さ分だけ繰り返し
                                v = i+setBeg;	//??
                                $('<div id="item' + data[v].itemNum + '" class="loadItem">' + data[v].itemSource + '</div>').appendTo(setArea).css({opacity:'0'}).animate({opacity:'1'},fadeSpeed);
                            }
                            setMore.remove();
                        }
                    } else if(loadItemLength == dataLengh){	//json==箱
                        return false;
                    }
                } else {
                    return false;
                }
            }, // success
            complete : function(){	//complateはanimate関数の引数。animate終了後に実行する関数の指定
                $('#nowLoading').each(function(){	//	nowloadingをremove
                    $(this).remove();
                });
                return false;
            } // complete
        });	// ajaxの終了タグ
        return false;
    });	//	クリックイベントの終了
});