package br.com.shared.constantes;

public interface Constantes {
    interface urlApi {
        String urlAPI = "http://localhost:8081/api";

        interface cadatroImobiliario {
            String urlCadastroImobiliario = urlAPI + "/cadastroImobiliario";
            String urlConsultarCadastroImobiliarioPorId = urlCadastroImobiliario + '/';
            String urlConsultarCadastroImobiliarioPorUsuario = urlCadastroImobiliario + "/listar/";
            String urlListarCadastroImobiliario = urlCadastroImobiliario + "/listar";
            String urlSalvarCadastroImobiliario = urlCadastroImobiliario + "/salvar";
            String urlAlterarCadastroImobiliario = urlCadastroImobiliario + "/alterar";
            String urlExcluirCadastroImobiliario = urlCadastroImobiliario + "/";
        }

        interface tipoLogradouro {
            String urlListarTipoLogradouro = urlAPI + "/cadastroImobiliario/tipoLogradouro/listar";
        }

        interface unidadeFederativa {
            String urlListarUnidadeFederativa = urlAPI + "/cadastroImobiliario/unidadeFederativa/listar";

        }

        interface usuario {
            String urlAutenticar = urlAPI + "/autenticar";
            String urlConsultarPorEmail = urlAPI + "/usuario/consultarPorEmail";
            String urlCadastrarUsuario = urlAPI + "/usuario/salvar";
        }

    }
}
