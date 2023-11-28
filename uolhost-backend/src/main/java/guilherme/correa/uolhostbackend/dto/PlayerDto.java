package guilherme.correa.uolhostbackend.dto;

import guilherme.correa.uolhostbackend.model.GroupType;

public record PlayerDto (String name, String email, String phone, GroupType groupType) {
}
