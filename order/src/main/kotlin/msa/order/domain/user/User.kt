package msa.order.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.function.Function
import java.util.stream.Collectors

class User(
    private var username: String? = null,
    private var password: String? = null,
    private val enabled: Boolean? = null,
    val roles: List<Role>? = null
) : UserDetails {
    override fun getUsername(): String {
        return username!!
    }

    fun setUsername(username: String?) {
        this.username = username
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
        return enabled!!
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles!!.stream().map(Function<Role, SimpleGrantedAuthority?> { authority: Role ->
            SimpleGrantedAuthority(
                authority.name
            )
        }).collect(Collectors.toList())
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password!!
    }

    @JsonProperty
    fun setPassword(password: String?) {
        this.password = password
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}