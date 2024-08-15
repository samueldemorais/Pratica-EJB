package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;


import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;


@Stateless(name = "usuarioService")
@Remote
public class UsuarioService {
    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());


    @EJB
    private UsuarioDAO usuarioDao;

    @EJB
    private MensagemDAO mensagemDao;

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public void inserir(long id, String nome) {
        Usuario novoUsuario = new Usuario(id, nome);
        usuarioDao.inserir(novoUsuario);

        try {
            Mensagem mensagem = new Mensagem();
            mensagemDao.inserir(mensagem);
            if ("caralho".equals(mensagem.getMensagem()) || "porra".equals(mensagem.getMensagem())) {
                throw new RuntimeException("Palavrão não é permitido");
            }
        } catch (RuntimeException e) {
            logger.severe("Erro ocorrido: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }


        if (id == 4L) {
            novoUsuario.setNome(nome + " alterado");
        }
        if (id==3L) {
            throw new RuntimeException("Menor de idade não permitido!");
        }
        if (id == 4L) {
            novoUsuario.setNome(nome + " alterado");
        }

    }
}
