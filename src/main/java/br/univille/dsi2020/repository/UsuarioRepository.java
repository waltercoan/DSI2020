package br.univille.dsi2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.univille.dsi2020.model.Usuario;

 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);
    Usuario findByUsuarioAndSenha(String usuario,String senha);
}
