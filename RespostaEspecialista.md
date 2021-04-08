#Resposta Especialista

Peso 0.25: Mapeamento simples para as classes Aluno e Avaliação
Peso 1: Mapeamento como @ManyToOne para as classes Resposta e AutoCorrecao
Peso 0.25: Criação de EntityManagerFactory, EntityManager e chamada do persist para salvar um objeto do tipo Aluno
Peso 0.25: Criação/Reaproveitamento de EntityManagerFactory, EntityManager e chamada do persist para salvar um objeto do tipo Avaliação
Peso 2: Criação/Reaproveitamento de EntityManagerFactory, EntityManager, carregamento do aluno, da avaliação e posterior persist da resposta
Peso 2: Criação/Reaproveitamento de EntityManagerFactory, EntityManager, carregamento da resposta e posterior persist da auto correção.
Peso 2.25: Carregamento da auto correção e navegação até o nome do(a) aluno(a) tomando cuidado com o lazy load
Peso 2: Carregamento de todas respostas de um(a) aluno(a) via query a partir de um(a) determinado(a) aluno(a).

- [x] Crio a classe Aluno, adiciono os atributos necessários e também adiciono um id. Adiciono o Id para mapear como chave primária para o banco de dados.
- [x] Mapeio a classe Aluno para ser entendida pelo HIbernate. Uso o @Entity em cima da classe e o @Id em cima do atributo id. Poderia colocar também o @Id no email. A @Entity explica para o Hibernate que aquela classe vai ser uma tabela no banco e o @Id explica que aquele atributo vai ser a chave primaria na tabela. Os outros atributos eu não preciso mapear pq ele já vai mapear para colunas do mesmo nome.
- [x] Crio a classe Avaliacao, adiciono os atributos necessários e também adiciono um id.
- [x] Mapeio a classe Avaliacao para ser entendida pelo HIbernate. Uso o @Entity em cima da classe e o @Id em cima do atributo id.
- [x] Crio a classe Respota, adiciono os atributos necessários. Um dos atributos aqui é do tipo Avaliação para fazer o link, além do atributo do tipo Aluno. Além disso adiciono o id. Uso o atributo do tipo Avaliacao e Aluno para manter o domínio rico e tirar proveito do ORM.
- [x] Mapeio a classe Resposta para ser entendida pelo HIbernate. Uso o @Entity em cima da classe e o @Id em cima do atributo id. Além disso preciso usar o @ManyToOne em cima do atributo que representa a Avaliacao, assim como no atributo Aluno. Dessa forma o Hibernate vai entender que tal atributo precisa ser mapeado como chave estrangeira na tabela.
- [x] Crio a classe AutoAvaliacao e adiciono os atributos necessários. Um dos atributos é do tipo Resposta, já que a autoavaliação é relativa a resposta que o(a) próprio(a) aluno(a) deu.  Além disso adiciono o id  como atributo extra para ser chave primária.
- [x] Mapeio a classe AutoAvaliacao para ser entendida pelo HIbernate. Uso o @Entity em cima da classe e o @Id em cima do atributo id. Além disso preciso usar o @ManyToOne em cima do atributo que representa a Resposta. Dessa forma o Hibernate vai entender que tal atributo precisa ser mapeado como chave estrangeira na tabela.
- [x] Para salvar um aluno eu crio um EntityManagerFactory, construo um EntityManager, crio o objeto do tipo Aluno, abro uma transação, invoco o método persist e faço um commit. Preciso da EMF porque ela é a fábrica de EM. O EM representa a ideia de conexão com o banco de dados.
- [x] Para salvar uma avaliacao eu crio um EntityManagerFactory, construo um EntityManager, crio o objeto do tipo Avaliacao, abro uma transação, invoco o método persist e faço um commit. Preciso da EMF porque ela é a fábrica de EM. O EM representa a ideia de conexão com o banco de dados.
- [x] Para salvar uma resposta eu crio um EntityManagerFactory, construo um EntityManager, carrego o objeto do tipo Avaliacao, carrego o objeto do tipo Aluno, crio o objeto do tipo Reposta, abro uma transação, invoco o método persist e faço um commit. Preciso da EMF porque ela é a fábrica de EM. O EM representa a ideia de conexão com o banco de dados. Preciso carregar os objetos do tipo Aluno e Avaliacao para construir o vínculo com a Resposta.
- [x] Para salvar uma auto correcao eu crio um EntityManagerFactory, construo um EntityManager, carrego o objeto do tipo Resposta, crio o objeto do tipo AutoCorrecao, abro uma transação, invoco o método persist e faço um commit. Preciso da EMF porque ela é a fábrica de EM. O EM representa a ideia de conexão com o banco de dados. Preciso carregar o objeto do tipo Resposta para fazer o vínculo com a AutoCorrecao.
- [x] Para descobrir o nome do(a) aluno(a) a partir da AutoCorrecao eu preciso navegar pelos relacionamentos @ManyToOne até chegar onde preciso. Isso pode ser feito através de métodos getters ou posso criar um método na AutoCorrecao que já me retorne o aluno em questão.
- [x] Para descobrir as respostas do aluno a partir do objeto do tipo Aluno é necessário fazer uma query planejada usando a JPQL ou navegar por relacionamentos. A ideia é realizar uma query sobre os objetos do tipo Resposta buscando todas que sejam daquele determinado(a) aluno(a). Algo como select r from Resposta r where r.aluno =:aluno .

