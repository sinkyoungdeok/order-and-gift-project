package msa.order.domain.user

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
    private var username: String,
    private var password: String,
    private val enabled: Boolean,
    val roles: List<Role>,
    var deletedAt: LocalDateTime? = null,
    var userToken: String
) : UserDetails {
    constructor(username: String, password: String, enabled: Boolean, roles: List<Role>) : this(
        null,
        username,
        password,
        enabled,
        roles,
        null,
        ""
    )

    constructor(
        username: String,
        password: String,
        enabled: Boolean,
        roles: List<Role>,
        userToken: String
    ) : this(
        null,
        username,
        password,
        enabled,
        roles,
        null,
        userToken
    )


    override fun getUsername(): String {
        return username
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