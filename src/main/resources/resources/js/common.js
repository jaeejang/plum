/**
 * 
 */

function decode(dict,type,code){
	for(var i=0; i< dict.length;i++){
		if(dict[i].code == code && dict[i].type == type){
			return dict[i].name;
		}
	}
}