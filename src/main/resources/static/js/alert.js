function Check() {
	var checked = confirm("ログインしますか？");
	if (checked == true) {
		return true;
	} else {
		return false;
	}
}
