//**********************************************************
//* �׎�I���w���v
//* 
//* ����
//* �׎�R�[�h
//* �Ǘ�����Ћ敪�I���t���O(�ݒ肵�Ȃ��ꍇ�͑I���\)
//* 	�O�F�Ǘ�����Ћ敪�R���{�I���\
//* 	�P�F�Ǘ�����Ћ敪�R���{�I��s��
//* 
//* �Ԃ�l
//* �O�F�׎�R�[�h
//* �P�F�׎��ЃR�[�h
//* �Q�F�׎喼
//* �R�F�X�֔ԍ�
//* �S�F�s���{���R�[�h
//* �T�F�s�撬���R�[�h
//* �U�F�Z���P
//* �V�F�Z���Q
//* �W�F�Z���R
//* �X�F�d�b�ԍ�
//* �P�O�F�׎�_��敪
//* �P�P�F�_�񎖋Ƌ敪
//* �P�Q�F�z���_��敪
//* �P�R�F����ԕi�_��敪
//* �P�S�F����_��敪
//* �P�T�F���Ӑ�R�[�h
//* �P�U�F�׎�J�i��
//* �P�V�F�׎嗪��
//* �P�W�F�׎匟������
//* �P�X�F�S���Җ�
//* �Q�O�F�����d�b�ԍ�
//* �Q�P�F�e�`�w�ԍ�
//* �Q�Q�F����U�֋敪
//* �Q�R�F����ϓ���O�敪
//* �Q�S�F�ב��l��
//* �Q�T�F�ב��l�J�i��
//* �Q�U�F�ב��l�X�֔ԍ�
//* �Q�V�F�ב��l�s���{���R�[�h
//* �Q�W�F�ב��l�s�撬���R�[�h
//* �Q�X�F�ב��l�Z���P
//* �R�O�F�ב��l�Z���Q
//* �R�P�F�ב��l�Z���R
//* �R�Q�F�ב��l�d�b�ԍ�
//* �R�R�F�ב��l�����d�b�ԍ�
//* �R�S�F�ב��l�e�`�w�ԍ�
//* �R�T�F�ב��l���l
//* �R�U�F�����n��敪
//* �R�V�F�^���̌n�R�[�h
//* �R�W�F�����̌n�R�[�h
//* �R�X�F�׎劄���̌n�R�[�h
//* �S�O�F�����v�Z�����敪
//**********************************************************
function PLC1H0010_sel(ninushiCd, flg){
	//�׎�w���v�\��
	var retData = new Array(41);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�׎�b�c
	goparm= "../../," + ninushiCd + "," + flg;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��׎�I���w���v�\��
	//flag= window.showModalDialog("/logistics/contents/html/PLC1H0010Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag= window.showModalDialog("/logistics/contents/html/PLC1H0010Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	
	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�S�P�B
		retValue=flag + ",";
		for (i = 0; i < 41; i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

// 2018/05/31 NTS_�� �yNo.B502�z�⍇���ꗗ(�N���[��/���) �̋@�\�ǉ��ɂ��� �C���˗��BADD Start
//**********************************************************
//* �폜�m�F�w���v
//**********************************************************
function PLC1H0170_sel(){
	var flag;
	flag= window.showModalDialog("/logistics/contents/html/PLC1H0170.html","","dialogWidth:260px;dialogHeight:150px;directories:no; localtion:no; menubar:no; status:0; toolbar:no;scrollbars:no;Resizeable:1;scroll:0;");
	
	if (flag==null || flag.length==0 || flag == "1"){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return false;
	} else {
		return true;
	}
}
// 2018/05/31 NTS_�� �yNo.B502�z�⍇���ꗗ(�N���[��/���) �̋@�\�ǉ��ɂ��� �C���˗��BADD End
//**********************************************************
//* �ƎґI���w���v
//* 
//* ����
//* �Ǝ҃R�[�h
//* �Ǘ�����Ћ敪�I���t���O(�ݒ肵�Ȃ��ꍇ�͑I����)
//* 	�O�F�Ǘ�����Ћ敪�R���{�I����
//* 	�P�F�Ǘ�����Ћ敪�R���{�I��s��
//* 
//* �Ԃ�l
//* �O�F�Ǝ҃R�[�h
//* �P�F�ƎҖ�
//* �Q�F�d�b�ԍ�
//* �R�F�d����R�[�h
//* �S�F�ŋ敪
//* �T�F�_��J�n��
//* �U�F�_��I����
//**********************************************************
function PLC1H0020_sel(gyousyaCd, flg){
	//�Ǝ҃w���v�\��
	var retData = new Array(7);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�Ǝ҃R�[�h
	goparm= "../../," + gyousyaCd + "," + flg;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��ƎґI���w���v�\��
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0020Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("../html/PLC1H0020Fram.html",goparm,"dialogHeight:630px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�V�B
		retValue=flag + ",";
		for (i = 0;i<7;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* �h���C�o�[�I���w���v
//* 
//* ����
//* �h���C�o�[�R�[�h�A���[�_�[�敪�A
//* �Ǘ�����Ћ敪�I���t���O(�ݒ肵�Ȃ��ꍇ�͑I����)
//* 	�O�F�Ǘ�����Ћ敪�R���{�I����
//* 	�P�F�Ǘ�����Ћ敪�R���{�I��s��
//* 
//* �Ԃ�l
//* �O�F�h���C�o�[�R�[�h
//* �P�F�h���C�o�[��
//* �Q�F�Ǝ҃R�[�h
//* �R�F�ƎҖ�
//* �S�F�g�ѓd�b�ԍ�
//* �T�F���[�_�敪
//* �U�F�_�񎖋Ƌ敪
//* �V�F�����敪
//* �W�F�f�|�R�[�h
//**********************************************************
function PLC1H0030_sel(driverCd,leaderKb, flg){
	//�h���C�o�[�w���v�\��
	var retData = new Array(9);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	leaderKb = "" + leaderKb;
	if (leaderKb == "undefined" || leaderKb == null){
		leaderKb = "";
	}
	//���s�p�����^�@1:���surl�p�X�@,2:�h���C�o�[�R�[�h�@,3:���[�_�[�敪
	goparm= "../../," + driverCd + "," + leaderKb + "," + flg;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��h���C�o�[�I���w���v�\��
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0030Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0030Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�X�B
		retValue=flag + ",";
		for (i = 0;i<9;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

// 2017/08/17 NTS_�� No.309 �h���C�o�[�����e�i���X�@�\�̉��C ADD Start
//**********************************************************
//* �h���C�o�[�I���w���v
//* 
//* ����
//* �h���C�o�[�R�[�h�A���[�_�[�敪�A
//* �Ǘ�����Ћ敪�I���t���O(�ݒ肵�Ȃ��ꍇ�͑I����)
//* 	�O�F�Ǘ�����Ћ敪�R���{�I����
//* 	�P�F�Ǘ�����Ћ敪�R���{�I��s��
//* 
//* �Ԃ�l
//* �O�F�h���C�o�[�R�[�h
//* �P�F�h���C�o�[��
//* �Q�F�Ǝ҃R�[�h
//* �R�F�ƎҖ�
//* �S�F�g�ѓd�b�ԍ�
//* �T�F���[�_�敪
//* �U�F�_�񎖋Ƌ敪
//* �V�F�����敪
//* �W�F�f�|�R�[�h
//**********************************************************
function PLC1H0031_sel(driverCd,leaderKb, flg){
	//�h���C�o�[�w���v�\��
	var retData = new Array(9);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	leaderKb = "" + leaderKb;
	if (leaderKb == "undefined" || leaderKb == null){
		leaderKb = "";
	}
	//���s�p�����^�@1:���surl�p�X�@,2:�h���C�o�[�R�[�h�@,3:���[�_�[�敪
	goparm= "../../," + driverCd + "," + leaderKb + "," + flg;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��h���C�o�[�I���w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0031Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�X�B
		retValue=flag + ",";
		for (i = 0;i<9;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}
// 2017/08/17 NTS_�� No.309 �h���C�o�[�����e�i���X�@�\�̉��C ADD End
//**********************************************************
//* �Z���I���w���v
//* 
//* ����
//* �X�֔ԍ�
//* �Ǘ�����Ћ敪�I���t���O(�ݒ肵�Ȃ��ꍇ�͑I����)
//* 	�O�F�Ǘ�����Ћ敪�R���{�I����
//* 	�P�F�Ǘ�����Ћ敪�R���{�I��s��
//* 
//* �Ԃ�l
//* �O�F�X�֔ԍ�
//* �P�F�Z���b�c
//* �Q�F�s���{����
//* �R�F�s�撬��
//* �S�F���̑��Z��
//**********************************************************
function PLC1H0060_sel(jyusyoCd){
	//�Z���w���v�\��
	var retData = new Array(5);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�X�֔ԍ�
	goparm= "../../," + jyusyoCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��Z���I���w���v�\��
//	flag=window.showModalDialog("/logistics/contents/html/PLC1H0060Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0060Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�T�B
		retValue=flag + ",";
		for (i = 0;i<5;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* �d����I���w���v
//* 
//* ����
//* �d����R�[�h
//* 
//* �Ԃ�l
//* �O�F�d����R�[�h
//* �P�F�d���於
//* �Q�F�d�b�ԍ�
//**********************************************************
function PLC1H0070_sel(shiiresakiCd){
	//�d����w���v�\��
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�d����R�[�h
	goparm= "../../," + shiiresakiCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��d����I���w���v�\��
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0070Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0070Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�R�B
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* ���Ӑ�I���w���v
//* 
//* ����
//* ���Ӑ�R�[�h
//* 
//* �Ԃ�l
//* �O�F�d����R�[�h
//* �P�F�d���於
//* �Q�F�d�b�ԍ�
//**********************************************************
function PLC1H0080_sel(tokuisakiCd){
	//���Ӑ�w���v�\��
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:���Ӑ�R�[�h
	goparm= "../../," + tokuisakiCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ�蓾�Ӑ�I���w���v�\��
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0080Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0080Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�R�B
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* �d���I���w���v
//* 
//* ����
//* �d���R�[�h
//* �Ǘ�����Ћ敪�I���t���O(�ݒ肵�Ȃ��ꍇ�͑I���\)
//* 	�O�F�Ǘ�����Ћ敪�R���{�I���\
//* 	�P�F�Ǘ�����Ћ敪�R���{�I��s��
//* 
//* �Ԃ�l
//* �O�F�d����R�[�h
//* �P�F�d���於
//* �Q�F�f�|�R�[�h
//* �R�F�Z���R�[�h
//* �S�F�Ǘ�����Ћ敪
//**********************************************************
function PLC1H0090_sel(shiwakeCd, flg){
	//�d���w���v�\��
	var retData = new Array(5);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�d���R�[�h
	goparm= "../../," + shiwakeCd+ "," + flg;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��d���I���w���v�\��
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0090Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0090Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�T�B
		retValue=flag + ",";
		for (i = 0;i<5;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* ����G���A�I���w���v
//* 
//* ����
//* ����G���A�R�[�h
//* 
//* �Ԃ�l
//* �O�F����G���A�R�[�h
//* �P�F����G���A��
//* �Q�F����G���A����
//**********************************************************
function PLC1H0100_sel(uriageAreaCd){
	//����G���A�w���v�\��
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:����G���A�R�[�h
	goparm= "../../," + uriageAreaCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ�蔄��G���A�I���w���v�\��
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0100Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0100Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�R�B
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* ������m�F
//* 
//* ����
//* ����ԍ�
//* 
//* �Ԃ�l�Ȃ�
//**********************************************************
function PLC1H0110_sel(okurijyouNo){
	//������m�F�\��
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:����ԍ�
	goparm= "../../," + okurijyouNo;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ�著����m�F�\��
	window.showModalDialog("/logistics/contents/html/PLC1H0110Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

}

//**********************************************************
//* �Ј��I���w���v
//* 
//* ����
//* ����R�[�h
//* �Ǘ�����Ћ敪�I���t���O(�ݒ肵�Ȃ��ꍇ�͑I���\)
//* 	�O�F�Ǘ�����Ћ敪�R���{�I���\
//* 	�P�F�Ǘ�����Ћ敪�R���{�I��s��
//* ����E�f�|�t���O
//* 	�P�F����
//* 	�Q�F�f�|
//* �Ԃ�l
//* �O�F���[�UID
//* �P�F���[�U�敪
//* �Q�F�Ј���
//* �R�F����R�[�h
//* �S�F�f�|�R�[�h
//**********************************************************
function PLC1H0120_sel(bumonCd, flg, flg2){
	//��t�ґI���w���v�\��
	var retData = new Array(4);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:����R�[�h,2:�Ǘ�����Ћ敪�I���t���O,3:����E�f�|�t���O
	goparm= "../../," + bumonCd + "," + flg + "," + flg2;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ���t�ґI���w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0120Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�T�B
		retValue=flag + ",";
		for (i = 0;i<5;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* ���q�l�I���w���v
//* 
//* ����
//* ���q�l����
//* 
//* �Ԃ�l
//* �O�F���q�l����
//* �P�F�׎�R�[�h
//* �Q�F�͂���R�[�h
//* �R�F�X�֔ԍ�
//* �S�F�s���{���R�[�h
//* �T�F�s�撬���R�[�h
//* �U�F�Z���P
//* �V�F�Z���Q
//* �W�F�Z���R
//* �X�F�d�b�ԍ�
//**********************************************************
function PLC1H0130_sel(userId){
	//���q�l�I���w���v�\��
	var retData = new Array(10);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:���[�UID
	goparm= "../../," + userId;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ�肨�q�l�I���w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0130Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�P�O�B
		retValue=flag + ",";
		for (i = 0;i<10;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* �c�Ə��������̓w���v
//* 
//* ����
//* �f�|�R�[�h
//* 
//* �Ԃ�l
//* �O�F��v����R�[�h
//* �P�F��s�R�[�h
//* �Q�F�x�X�R�[�h
//* �R�F�������
//* �S�F�����ԍ�
//* �T�F��s��
//* �U�F�x�X��
//* �V�F������ʖ�
//**********************************************************
function PLC1H0140_sel(depoCd){
	//�c�Ə��������̓w���v�\��
	var retData = new Array(8);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�f�|�R�[�h
	goparm= "../../," + depoCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��c�Ə��������̓w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0140Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�V�B
		retValue=flag + ",";
		for (i = 0;i<8;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* �^���̌n�I���w���v
//* 
//* ����
//* �^���̌n�R�[�h
//* 
//* �Ԃ�l
//* �O�F�Ǘ�����Ћ敪
//* �P�F�^���̌n�R�[�h
//* �Q�F�^���̌n����
//**********************************************************
function PLC1H0150_sel(unchintaikeiCd){
	//�^���̌n�I���w���v�\��
	var retData = new Array(10);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�^���̌n�R�[�h
	goparm= "../../," + unchintaikeiCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��^���̌n�I���w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0150Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�P�O�B
		retValue=flag + ",";
		for (i = 0;i<10;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}


//**********************************************************
//* ��p�Ώۏ�񒊏o���
//* 
//* ����
//* 
//* �Ԃ�l
//* 
//**********************************************************
function PLC1B0013_sel(depoCd){
	//��p�Ώۏ�񒊏o��ʕ\��
	var retData = new Array(8);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�f�|�R�[�h
	goparm= "../../," + depoCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��c�Ə��������̓w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1B0013Fram.html",document.forms[0],"dialogHeight:250px; dialogWidth:670px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		return null;
	} else {
		return null;
	}
}
//**********************************************************
//* ��p�Ώۏ��捞���
//* 
//* ����
//* 
//* �Ԃ�l
//* 
//**********************************************************
function PLC1B0014_sel(depoCd){
	//��p�Ώۏ�񒊏o��ʕ\��
	var retData = new Array(8);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�f�|�R�[�h
	goparm= "../../," + depoCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��c�Ə��������̓w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1B0014Fram.html",goparm,"dialogHeight:500px; dialogWidth:700px; scroll:0; status:0; resizable:1;");
	if (flag==null || flag.length==0){
		return null;
	} else {
		return null;
	}
}
//**********************************************************
//* �S���s����I���w���v
//* 
//* ����
//* �S���s����b�c
//* 
//* �Ԃ�l
//* �O�F�S���s����CD
//* �P�F�s���{����
//* �Q�F�s�撬��
//**********************************************************
function PLC1H0160_sel(jyusyoCd){
	//�Z���w���v�\��
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//���s�p�����^�@1:���surl�p�X�@,2:�S���s����b�c
	goparm= "../../," + jyusyoCd;
	//���͌��������`�F�b�N
	//�_�C�A���O�ɂ��Z���I���w���v�\��
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0160Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//�߂�l����̏ꍇ�͕���Ŗ߂����ꍇ�B
		return null;
	} else {
		//�߂�l�R�B
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}