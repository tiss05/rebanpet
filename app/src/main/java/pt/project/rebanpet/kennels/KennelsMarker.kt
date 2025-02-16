package pt.project.rebanpet.kennels

import com.google.android.gms.maps.model.LatLng

object KennelsMarker {
    val markers = listOf(
        //Açores
        MarkerData("Canil Intermunicipal da Ilha Terceira", LatLng(37.74, -25.67)),
        MarkerData("Canil Municipal São Roque do Pico", LatLng(40.64, -8.64)),
        MarkerData("Canil Municipal das Lajes do Pico", LatLng(40.64, -8.64)),
        MarkerData("Canil Municipal de Ponta Delgada", LatLng(40.64, -8.64)),
        MarkerData("Canil Municipal da Ribeira Grande", LatLng(40.64, -8.64)),
        MarkerData("Canil Municipal da Lagoa", LatLng(40.64, -8.64)),
        MarkerData("Associação Cantinho dos Animais dos Açores", LatLng(40.64, -8.64)),
        MarkerData("Coletivo Alice Moderno", LatLng(40.64, -8.64)),
        MarkerData("Associação Amigos dos Animais da Ilha Terceira", LatLng(40.64, -8.64)),
        MarkerData("Afama Faial", LatLng(40.64, -8.64)),
        MarkerData("AAAIG - Associação dos Amigos dos Animais da Ilha Graciosa", LatLng(40.64, -8.64)),
        MarkerData("Santuário Animal do Pico", LatLng(40.64, -8.64)),
        MarkerData("CRO Animais Lajes Flores", LatLng(40.64, -8.64)),

        //Madeira
        MarkerData("Canil Municipal do Funchal", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Casa da Violeta", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Santa Cruz", LatLng(38.0167, -7.8633)),
        MarkerData("Abrigo Municipal do Machico", LatLng(38.0167, -7.8633)),

        //Faro
        MarkerData("Canil Municipal de Lagoa - Cantinho dos Animais", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Portimão", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Faro", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Loulé", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Olhão", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Lagos", LatLng(38.0167, -7.8633)),
        MarkerData("Associação de Defesa dos Animais de Albufeira (ADAA)", LatLng(38.0167, -7.8633)),
        MarkerData("Associação Animais de Rua", LatLng(38.0167, -7.8633)),
        MarkerData("Associação Cão Algarve", LatLng(38.0167, -7.8633)),

        //Beja
        MarkerData("Canil Municipal de Beja", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Moura", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Serpa", LatLng(38.0167, -7.8633)),
        MarkerData("Associação de Defesa do Património de Mértola (ADPM)", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Aljustrel", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Castro Verde", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Odemira", LatLng(38.0167, -7.8633)),
        MarkerData("Associações de Proteção Animal", LatLng(38.0167, -7.8633)),
        MarkerData("ACGRA - Associação Canil e Gatil Os Rafeiritos do Alentejo", LatLng(38.0167, -7.8633)),

        //Évora
        MarkerData("Canil Municipal de Évora", LatLng(38.0167, -7.8633)),
        MarkerData("Associação de Defesa do Património de Évora (ADPE)", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Montemor-o-Novo", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Viana do Alentejo", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Reguengos de Monsaraz", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Redondo", LatLng(38.0167, -7.8633)),
        MarkerData("Associação Animais de Rua", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Portel", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Estremoz", LatLng(38.0167, -7.8633)),
        MarkerData("Canil de Vila Viçosa", LatLng(38.0167, -7.8633)),

        //Setúbal
        MarkerData("Canil Municipal de Setúbal", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal do Barreiro", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Almada", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Palmela", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Montijo", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Sesimbra", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Alcácer do Sal", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Santiago do Cacém", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Grândola", LatLng(38.0167, -7.8633)),
        MarkerData("Canil Municipal de Sines", LatLng(38.0167, -7.8633)),

        //Lisboa
        MarkerData("Canil Municipal de Lisboa", LatLng(38.764998, -9.103658)),
        MarkerData("Canil de Sintra", LatLng(38.802328, -9.298172)),
        MarkerData("Canil de Oeiras", LatLng(38.686457, -9.310483)),
        MarkerData("Canil de Loures", LatLng(38.843478, -9.164578)),
        MarkerData("Associação Zoófila Portuguesa", LatLng(38.764313, -9.243943)),
        MarkerData("Canil de Almada", LatLng(38.641748, -9.183738)),
        MarkerData("Canil de Amadora", LatLng(38.764313, -9.243943)),

        //Leiria
        MarkerData("Canil Municipal de Leiria", LatLng(39.758329, -8.789825)),
        MarkerData("Associação Zoófila de Leiria", LatLng(39.7475, -8.8057)),
        MarkerData("Canil Municipal da Marinha Grande", LatLng(39.914841, -8.932636)),
        MarkerData("Canil Municipal de Pombal", LatLng(39.907947, -8.633505)),
        MarkerData("Canil Intermunicipal de Ourém e Fátima", LatLng(39.640285, -8.557587)),

        //Santarém
        MarkerData("Canil Municipal de Santarém", LatLng(39.235273, -8.682327)),
        MarkerData("Canil Municipal de Abrantes", LatLng(39.456434, -8.213701)),
        MarkerData("Canil Municipal de Tomar", LatLng(39.616838, -8.437755)),
        MarkerData("Canil Municipal de Torres Novas", LatLng(39.485832, -8.531517)),
        MarkerData("Canil Municipal de Rio Maior", LatLng(39.339481, -8.935935)),
        MarkerData("Canil Municipal de Almeirim", LatLng(39.207937, -8.538820)),
        MarkerData("Canil Municipal de Benavente", LatLng(38.921712, -8.780892)),
        MarkerData("Associação Scalabitana de Proteção de Animais (ASPA)", LatLng(39.2487208, -8.7148)),
        MarkerData("Canil Municipal de Coruche", LatLng(38.964884, -8.526524)),

        //Portalegre
        MarkerData("Canil Municipal de Portalegre", LatLng(39.288042, -7.447741)),
        MarkerData("Canil Municipal de Castelo de Vide", LatLng(39.393953, -7.452289)),
        MarkerData("Canil Municipal de Marvão", LatLng(39.396562, -7.392293)),
        MarkerData("Canil Municipal de Nisa", LatLng(39.590181, -7.653918)),
        MarkerData("Canil Municipal de Ponte de Sor", LatLng(39.245573, -8.003097)),
        MarkerData("Canil Municipal de Gavião", LatLng(39.478158, -7.938587)),
        MarkerData("Canil Municipal de Alter do Chão", LatLng(39.205668, -7.666818)),
        MarkerData("Canil Municipal de Sousel", LatLng(39.070830, -7.790632)),
        MarkerData("Canil Municipal de Crato", LatLng(39.278214, -7.627224)),
        MarkerData("Associações de Proteção Animal", LatLng(39.294, -7.433)),
        MarkerData("Associação Patas Dadas", LatLng(39.3931, -7.3764)),

        //Castelo Branco
        MarkerData("Canil Municipal de Castelo Branco", LatLng(39.820804, -7.505056)),
        MarkerData("Canil Municipal de Vila Velha de Ródão", LatLng(39.648658, -7.842220)),
        MarkerData("Canil Municipal de Idanha-a-Nova", LatLng(39.915209, -6.889855)),
        MarkerData("Canil Municipal de Oleiros", LatLng(39.952868, -7.910692)),
        MarkerData("Canil Municipal de Proença-a-Nova", LatLng(39.739047, -7.943727)),
        MarkerData("Canil Municipal de Sertã", LatLng(39.804499, -8.105334)),
        MarkerData("Canil Municipal de Covilhã", LatLng(40.282813, -7.497422)),
        MarkerData("Associação de Defesa dos Animais de Castelo Branco (ADACB)", LatLng(39.823, -7.491)),
        MarkerData("Associação Animais de Rua", LatLng(39.822, -7.498)),
        MarkerData("Canil Municipal de Fundão", LatLng(40.145474, -7.498693)),

        //Coimbra
        MarkerData("Canil Municipal de Coimbra", LatLng(40.206215, -8.464778)),
        MarkerData("Canil Municipal de Cantanhede", LatLng(40.419578, -8.584358)),
        MarkerData("Canil Municipal de Figueira da Foz", LatLng(40.144657, -8.855217)),
        MarkerData("Canil Municipal de Montemor-o-Velho", LatLng(40.166176, -8.668579)),
        MarkerData("Canil Municipal de Miranda do Corvo", LatLng(40.095142, -8.348545)),
        MarkerData("Associação Animais de Rua", LatLng(40.2111, -8.4291)),
        MarkerData("Associação de Defesa dos Animais de Coimbra (ADAC)", LatLng(40.2093, -8.44)),
        MarkerData("Canil Municipal de Penacova", LatLng(40.243426, -8.277968)),
        MarkerData("Canil Municipal de Soure", LatLng(40.051705, -8.626818)),
        MarkerData("Canil Municipal de Condeixa-a-Nova", LatLng(40.099538, -8.495012)),

        //Guarda
        MarkerData("Canil Municipal da Guarda", LatLng(40.5422, -7.2717)),
        MarkerData("Canil Municipal de Pinhel", LatLng(40.8193, -7.1668)),
        MarkerData("Canil Municipal de Trancoso", LatLng(40.7732, -7.3575)),
        MarkerData("Associação Animais de Rua", LatLng(40.5353, -7.2674)),
        MarkerData("Canil Municipal de Sabugal", LatLng(40.3446, -7.0889)),
        MarkerData("Canil Municipal de Almeida", LatLng(40.7239, -6.9127)),
        MarkerData("Associação de Defesa dos Animais de Vilar Formoso", LatLng(40.7428, -6.8653)),
        MarkerData("Canil Municipal de Celorico da Beira", LatLng(40.5829, -7.4174)),
        MarkerData("Canil Municipal de Mêda", LatLng(40.9688, -7.2653)),
        MarkerData("Canil Municipal de Figueira de Castelo Rodrigo", LatLng(40.8234, -6.9689)),

        //Viseu
        MarkerData("Canil Municipal de Viseu", LatLng(40.661357, -7.924388)),
        MarkerData("Canil Municipal de Vouzela", LatLng(40.744134, -8.139858)),
        MarkerData("Canil Municipal de Tondela", LatLng(40.51654, -8.08433)),
        MarkerData("Canil Municipal de São Pedro do Sul", LatLng(40.75359, -8.05924)),
        MarkerData("Canil Municipal de Castro Daire", LatLng( 40.84532, -7.91231)),
        MarkerData("Canil Municipal de Mangualde", LatLng(40.59943, -7.77945)),
        MarkerData("Canil Municipal de Nelas", LatLng(40.52979, -7.82633)),
        MarkerData("Canil Municipal de Oliveira de Frades", LatLng(40.71046, -8.30201)),
        MarkerData("Associação Animais de Rua", LatLng(40.65515, -7.91426)),
        MarkerData("Canil Municipal de Sátão", LatLng(40.72833, -7.76182)),

        //Aveiro
        MarkerData("Canil Municipal de Aveiro", LatLng(40.6282599, -8.6290541)),
        MarkerData("Canil Municipal de Águeda", LatLng(40.5791667, -8.4697222)),
        MarkerData("Canil Municipal de Albergaria-a-Velha", LatLng(40.768333, -8.466944)),
        MarkerData("Canil Municipal de Arouca", LatLng(40.9211179, -8.2433751)),
        MarkerData("Canil Municipal de Espinho", LatLng(41.016944, -8.643056)),
        MarkerData("Canil Municipal de Mealhada", LatLng(40.414855, -8.450446)),
        MarkerData("Canil Municipal de Ovar", LatLng(40.8628137, -8.6334583)),
        MarkerData("Canil Municipal de Vagos", LatLng(40.5245, -8.6608)),

        //Porto
        MarkerData("Canil Municipal do Porto", LatLng(41.182876, -8.577933)),
        MarkerData("Canil Intermunicipal de Vila do Conde", LatLng(41.3479, -8.7251)),
        MarkerData("Canil Municipal de Matosinhos", LatLng(41.214987, -8.664478)),
        MarkerData("Canil Municipal de Gaia", LatLng(41.1325262, -8.6210927)),
        MarkerData("Canil Municipal de Gondomar", LatLng(41.148745, -8.508033)),
        MarkerData("Canil Municipal de Maia", LatLng(41.2448815, -8.6153594)),
        MarkerData("Canil Municipal de Póvoa de Varzim", LatLng(41.363383, -8.706833)),
        MarkerData("Associação Animais de Rua", LatLng(41.1908865, -8.6535827)),
        MarkerData("Associação Porto dos Animais", LatLng(41.1734556, -8.6345782)),
        MarkerData("Canil Municipal de Valongo", LatLng( 41.1839, -8.4832)),

        //Braga
        MarkerData("Canil Municipal de Braga", LatLng(41.561155, -8.399878)),
        MarkerData("Canil Municipal de Guimarães", LatLng(41.440479, -8.318149)),
        MarkerData("Canil Municipal de Barcelos", LatLng(41.5356, -8.6172)),
        MarkerData("Canil Municipal de Vila Nova de Famalicão", LatLng(41.4069428, -8.5151121)),
        MarkerData("Canil Municipal de Esposende", LatLng(41.5293, -8.7795)),
        MarkerData("Canil Municipal de Fafe", LatLng(41.4484, -8.1764)),
        MarkerData("Canil Municipal de Vizela", LatLng(41.436867, -8.298627)),
        MarkerData("Canil Municipal de Amares", LatLng(41.6556, -8.3336)),

        //Viana do Castelo
        MarkerData("Canil Municipal de Viana do Castelo", LatLng(41.692, -8.822)),
        MarkerData("Associação Animais de Rua", LatLng(41.6925, -8.8328)),
        MarkerData("Canil Intermunicipal do Cávado e do Ave", LatLng(41.535, -8.428)),
        MarkerData("Associação de Defesa dos Animais de Viana do Castelo (ADAVC)", LatLng(41.6945, -8.8275)),
        MarkerData("Canil de Ponte de Lima", LatLng(41.7667, -8.5833)),
        MarkerData("Canil de Caminha", LatLng(41.8657, -8.8737)),
        MarkerData("Associação Patinhas D’Aurora", LatLng(41.547, -8.426)),
        MarkerData("Canil de Arcos de Valdevez", LatLng(41.8542, -8.4167)),
        MarkerData("Associação Amigos dos Animais de Vila Nova de Cerveira", LatLng( 41.945386, -8.730246)),
        MarkerData("Canil de Valença", LatLng(42.0251857, -8.6444705)),

        //Vila Real
        MarkerData("Canil Municipal de Vila Real", LatLng(41.3, -7.7333)),
        MarkerData("Canil Intermunicipal do Alto Tâmega", LatLng(41.745, -7.47)),
        MarkerData("Associação Animais de Rua", LatLng(41.3008577, -7.7421333)),
        MarkerData("Canil de Montalegre", LatLng(41.8333, -7.7928)),
        MarkerData("Canil de Chaves", LatLng(41.7333, -7.4667)),
        MarkerData("Associação de Defesa dos Animais de Vila Real (ADAVR)", LatLng(41.3015, -7.7422)),
        MarkerData("Canil de Peso da Régua", LatLng(41.1705, -7.7918)),
        MarkerData("Canil de Sabrosa", LatLng(41.345, -7.523)),
        MarkerData("Canil de Alijó", LatLng(41.2763, -7.4649)),
        MarkerData("Canil de Murça", LatLng(41.3667, -7.4167)),

        //Bragança
        MarkerData("Canil Municipal de Bragança", LatLng(41.80616520, -6.75674270)),
        MarkerData("Canil Municipal de Miranda do Douro", LatLng(41.4959, -6.27386)),
        MarkerData("Canil Municipal de Vimioso", LatLng(41.583931, -6.527730)),
        MarkerData("Canil Municipal de Macedo de Cavaleiros", LatLng(41.4942, -7.0045)),
        MarkerData("Canil Municipal de Vila Flor", LatLng(41.416667, -7.166667)),
        MarkerData("Canil Municipal de Mirandela", LatLng(41.48739, -7.18695)),
        MarkerData("Canil Municipal de Alfândega da Fé", LatLng(41.344363, -6.9601807)),
        MarkerData("Canil Municipal de Carrazeda de Ansiães", LatLng(41.242199, -7.3072)),
        MarkerData("Canil Municipal de Mogadouro", LatLng(41.33, -6.71)),
        MarkerData("Associação de Proteção Animal de Bragança", LatLng(41.807178, -6.75))
    )
}

data class MarkerData(
    val name: String,
    val coordinates: LatLng
)