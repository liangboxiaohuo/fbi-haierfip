/**
 * ��������ȡһ������
 * */
function $N(obj_name) {
	return document.getElementsByName(obj_name)[0];
}
/**
 * ��������ȡ�����valueֵ
 * */
function $NF(obj_name) {
	var obj = $N(obj_name);
	return obj.value.Trim();
}

function checkLinkname(obj_name) {
	var obj = $N(obj_name);
	var pname = $NF(obj_name);
	var name = $NF('NAME');
	if(pname == name) {
		alert("������ϵ�����������뱾��������ͬ��");
		obj.select();
		//obj.focus();
		return false;
	}
	return true;
}

function checkLinkPhone(obj_name) {
	var obj = $N(obj_name);
	var linkPhone = $NF(obj_name);
	var phone = $NF('PHONE1');
	if(phone == linkPhone) {
		alert("������ϵ���ֻ����벻���뱾���ֻ�������ͬ��");
		obj.select();
		//obj.focus();
		return false;
	}
	return true;
}

function checkAmt() {
	var v_AMT = $NF('TOTALAMT');
	var v_RECEIVEAMT = $NF('RECEIVEAMT');

	if (v_AMT >= 10000) {
		var v_r = Math.floor(v_AMT * 0.2);
		if(v_RECEIVEAMT < v_r) {
			alert('�׸��������' + v_r + "Ԫ��");
			$N('RECEIVEAMT').select();
			return false;
		}
	}
	return true;
}