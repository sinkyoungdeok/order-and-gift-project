package msa.gift.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.stream.Collectors

@Document
class User(
    @Id var id: String? = null,
    var name: String,
    var loginId: String,
    private var password: String,
    var enabled: Boolean,
    val roles: List<Role>,
    var deletedAt: LocalDateTime? = null,
    var userToken: String
) : UserDetails {
    constructor(
        name: String,
        loginId: String,
        password: String,
        enabled: Boolean,
        roles: List<Role>
    ) : this(
        null,
        name,
        loginId,
        password,
        enabled,
        roles,
        null,
        ""
    )

    constructor(
        name: String,
        loginId: String,
        password: String,
        enabled: Boolean,
        roles: List<Role>,
        userToken: String
    ) : this(
        null,
        name,
        loginId,
        password,
        enabled,
        roles,
        null,
        userToken
    )

    fun comeBack() {
        this.enabled = true
    }

    fun quit() {
        this.enabled = false
    }

    override fun getUsername(): String {
        return loginId
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun getAuthorities(): MutableList<SimpleGrantedAuthority> {
        return roles.stream().map { authority: Role ->
            SimpleGrantedAuthority(
                authority.name
            )
        }.collect(Collectors.toList())
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password
    }

    @JsonProperty
    fun setPassword(password: String) {
        this.password = password
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}