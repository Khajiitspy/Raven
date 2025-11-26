package Graveyard.seeders;

import Graveyard.entities.location.CountryEntity;
import Graveyard.entities.location.CityEntity;
import Graveyard.entities.account.RoleEntity;
import Graveyard.entities.account.UserEntity;
import Graveyard.repository.ICountryRepository;
import Graveyard.repository.ICityRepository;
import Graveyard.repository.IRoleRepository;
import Graveyard.repository.IUserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedDatabase(
            ICountryRepository countryRepo,
            ICityRepository cityRepo,
            IRoleRepository roleRepo,
            IUserRepository userRepo,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            RoleEntity adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> {
                        RoleEntity r = new RoleEntity();
                        r.setName("ADMIN");
                        return roleRepo.save(r);
                    });

            RoleEntity userRole = roleRepo.findByName("USER")
                    .orElseGet(() -> {
                        RoleEntity r = new RoleEntity();
                        r.setName("USER");
                        return roleRepo.save(r);
                    });


            if (countryRepo.count() == 0) {
                CountryEntity bulgaria = new CountryEntity();
                bulgaria.setName("Bulgaria");
                bulgaria.setCode("BG");
                bulgaria.setSlug("bulgaria");
                bulgaria.setImage(null);
                bulgaria = countryRepo.save(bulgaria);

                CountryEntity germany = new CountryEntity();
                germany.setName("Germany");
                germany.setCode("DE");
                germany.setSlug("germany");
                germany.setImage(null);
                germany = countryRepo.save(germany);


                CityEntity sofia = new CityEntity();
                sofia.setName("Sofia");
                sofia.setCountry(bulgaria);

                CityEntity plovdiv = new CityEntity();
                plovdiv.setName("Plovdiv");
                plovdiv.setCountry(bulgaria);

                CityEntity berlin = new CityEntity();
                berlin.setName("Berlin");
                berlin.setCountry(germany);

                CityEntity munich = new CityEntity();
                munich.setName("Munich");
                munich.setCountry(germany);

                cityRepo.saveAll(List.of(sofia, plovdiv, berlin, munich));
            }


            if (userRepo.count() == 0) {
                UserEntity admin = new UserEntity();
                admin.setName("System");
                admin.setLastName("Administrator");
                admin.setEmail("admin@example.com");
                admin.setPhone("0000000000");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole, userRole));
                userRepo.save(admin);

                UserEntity normalUser = new UserEntity();
                normalUser.setName("John");
                normalUser.setLastName("Doe");
                normalUser.setEmail("john@example.com");
                normalUser.setPhone("1234567890");
                normalUser.setPassword(passwordEncoder.encode("password"));
                normalUser.setRoles(Set.of(userRole));
                userRepo.save(normalUser);
            }

            System.out.println(" Database seeding completed.");
        };
    }
}
