package guilherme.correa.uolhostbackend.service;

import guilherme.correa.uolhostbackend.dto.PlayerDto;
import guilherme.correa.uolhostbackend.infra.CodinameHandler;
import guilherme.correa.uolhostbackend.model.GroupType;
import guilherme.correa.uolhostbackend.model.PlayerModel;
import guilherme.correa.uolhostbackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private CodinameHandler handler;

    public PlayerModel createPlayer(PlayerDto dto) {
        PlayerModel newPlayer = new PlayerModel(dto);
        String codiname = getCodiname(dto.groupType());
        newPlayer.setCodiname(codiname);
        return repository.save(newPlayer);

    }

    private String getCodiname(GroupType groupType){
        return handler.findCodiname(groupType);
    }

    public List<PlayerModel> getAllPlayers() {
        return repository.findAll();
    }
}
