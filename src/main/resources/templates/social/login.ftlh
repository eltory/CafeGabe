<button onclick="popupKakaoLogin()">KakaoLogin</button>
<script type="text/javascript">
    function popupKakaoLogin() {
        window.open("${loginUrl}".replace(/&amp;/g, "&"), 'popupKakaoLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')
    }
</script>
