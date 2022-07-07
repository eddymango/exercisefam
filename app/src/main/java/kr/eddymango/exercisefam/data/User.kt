package kr.eddymango.exercisefam.data

data class User (
    // 로그인 상태 저장 -> 디폴트 값 : false
    // 이름 + 아이디 + 비밀번호
    var loginMode:Boolean = false,
    var userName:String?= null,
    var password:String? = null,
    var userPhoneNum:String? = null
)


