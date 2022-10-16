package com.book.store.Model.RequestModel;

import com.book.store.Model.Role;
import com.book.store.Security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Integers;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {
    String username;
    String password;
    Set<Integer> authorities;
}
