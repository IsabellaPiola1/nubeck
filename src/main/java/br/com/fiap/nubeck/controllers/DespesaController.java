import org.springframework.web.bind.annotation.RestController;

@RestController
public class DespesaController {

    @GetMapping("/api/v1/despesas")
    public String index(){
        return new Despesa(1, new BigDecimal(100), localDate.now(), "cinema");
    }
    
}
