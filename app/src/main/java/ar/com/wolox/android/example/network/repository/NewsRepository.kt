package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.services.NewsService
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

/**
 * User Repositroy class
 */
class NewsRepository @Inject internal constructor(private val retrofitServices: RetrofitServices) {
    val news: List<News> = listOf(
            News("2016-07-18T14:00:29.985Z",
                    1,
                    "¿Famosos y sólo amigos?",
                    "http://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg",
                    "Ser súper estrellas e íntimos amigos tiene sus desventajas, al menos para George. " +
                            "Su esposa, Amal, es muy celosa de Julia e irrumpió varias veces en las grabaciones " +
                            "de su última peli juntos, aunque nunca pescó nada raro.",
                    mutableListOf(1, 3)),
            News("2016-07-18T14:01:38.673Z",
                    2,
                    "Hipnosis: la nueva vedette de las neurociencias",
                    "http://bucket1.glanacion.com/anexos/fotos/50/2082050.jpg",
                    "Hace un año, Marisa Bello, bibliotecóloga de La Plata, separada, 51 años, " +
                            "condujo un auto ultralujoso. Luego, durmió profundamente. Más tarde, se" +
                            " rió a carcajadas y olió un perfume indescriptible. Todo eso lo vivió " +
                            "desde una silla, apostada en el escenario de un pabellón de Tecnópolis.",
                    mutableListOf()),
            News("2016-07-18T13:53:13.735Z",
                    3,
                    "Como cuidar los muebles de cuero",
                    "http://bucket3.glanacion.com/anexos/fotos/28/soluciones-2231528w620.jpg",
                    "Generalmente los muebles de cuero son elegidos para destacarse porque " +
                            "resultan muy elegantes. Si bien el material es duradero, requiere de un" +
                            " mayor cuidado y mantenimiento que otros tipos de telas.",
                    mutableListOf(3)),
            News("2016-07-18T14:00:29.985Z",
                    1,
                    "¿Famosos y sólo amigos?",
                    "http://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg",
                    "Ser súper estrellas e íntimos amigos tiene sus desventajas, al menos para George. " +
                            "Su esposa, Amal, es muy celosa de Julia e irrumpió varias veces en las grabaciones " +
                            "de su última peli juntos, aunque nunca pescó nada raro.",
                    mutableListOf(1, 3)),
            News("2016-07-18T14:01:38.673Z",
                    2,
                    "Hipnosis: la nueva vedette de las neurociencias",
                    "http://bucket1.glanacion.com/anexos/fotos/50/2082050.jpg",
                    "Hace un año, Marisa Bello, bibliotecóloga de La Plata, separada, 51 años, " +
                            "condujo un auto ultralujoso. Luego, durmió profundamente. Más tarde, se" +
                            " rió a carcajadas y olió un perfume indescriptible. Todo eso lo vivió " +
                            "desde una silla, apostada en el escenario de un pabellón de Tecnópolis.",
                    mutableListOf()),
            News("2016-07-18T13:53:13.735Z",
                    3,
                    "Como cuidar los muebles de cuero",
                    "http://bucket3.glanacion.com/anexos/fotos/28/soluciones-2231528w620.jpg",
                    "Generalmente los muebles de cuero son elegidos para destacarse porque " +
                            "resultan muy elegantes. Si bien el material es duradero, requiere de un" +
                            " mayor cuidado y mantenimiento que otros tipos de telas.",
                    mutableListOf(3))
    )

    fun service(): NewsService {
        return retrofitServices.getService(NewsService::class.java)
    }
}