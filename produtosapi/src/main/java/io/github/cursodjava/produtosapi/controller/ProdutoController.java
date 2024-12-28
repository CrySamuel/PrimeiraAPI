package io.github.cursodjava.produtosapi.controller;

import io.github.cursodjava.produtosapi.model.Produto;
import io.github.cursodjava.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido: " + produto);

        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("{id}")
    public Produto obterPorId(@PathVariable("id") String id){
//        Optional<Produto> produto = produtoRepository.findById(id);
//        return produto.isPresent() ? produto.get() : null;
        System.out.println("Id Obtido: " + id);
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id){
        System.out.println("Id Deletado: " + id);
        produtoRepository.deleteById(id);
    }

    @PutMapping({"{id}"})
    public void atualizar(@PathVariable("id") String id, @RequestBody Produto produto ){
        System.out.println("Id Atualizado: " + id);
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto>buscar(@RequestParam("nome") String nome){
        System.out.println("Nome buscado: " + nome);
        return produtoRepository.findByNome(nome);
    }

}
