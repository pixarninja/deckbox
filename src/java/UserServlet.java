import beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import secure.EmailUtility;


@WebServlet(urlPatterns = {"/UserServlet"})
@MultipartConfig
public class UserServlet extends HttpServlet {
    
    public UserServlet() {
        super();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception ex) {
            ;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username;
        if((String)request.getAttribute("username") == null) {
            username = request.getParameter("username");
        }
        else {
            username = (String)request.getAttribute("username");
        }
        if(username == null || username.equals("null")) {
            username = "";
        }
        String password = request.getParameter("password");
        password = secure.DBConnection.SALT + password;
        password = secure.DBConnection.generateHash(password);
        String url = "/";
        String[] icons = {"icons/3d-meeple.png", "icons/3d-stairs.png", "icons/abacus.png", "icons/ace.png", "icons/achievement.png", "icons/achilles-heel.png", "icons/acid-blob.png", "icons/acid-tube.png", "icons/acid.png", "icons/acorn.png", "icons/acoustic-megaphone.png", "icons/acrobatic.png", "icons/aerial-signal.png", "icons/aerodynamic-harpoon.png", "icons/aerosol.png", "icons/africa.png", "icons/afterburn.png", "icons/ages.png", "icons/air-balloon.png", "icons/air-force.png", "icons/air-zigzag.png", "icons/airplane.png", "icons/airtight-hatch.png", "icons/ak47.png", "icons/ak47u.png", "icons/alarm-clock.png", "icons/algae.png", "icons/alien-bug.png", "icons/alien-egg.png", "icons/alien-fire.png", "icons/alien-skull.png", "icons/alien-stare.png", "icons/all-for-one.png", "icons/all-seeing-eye.png", "icons/allied-star.png", "icons/alligator-clip.png", "icons/amber-mosquito.png", "icons/ambulance.png", "icons/american-football-ball.png", "icons/american-football-helmet.png", "icons/american-shield.png", "icons/amethyst.png", "icons/ammo-box.png", "icons/ammonite-fossil.png", "icons/ammonite.png", "icons/amphora.png", "icons/amplitude.png", "icons/amputation.png", "icons/anarchy.png", "icons/anatomy.png", "icons/anchor.png", "icons/ancient-ruins.png", "icons/ancient-sword.png", "icons/android-mask.png", "icons/andromeda-chain.png", "icons/angel-outfit.png", "icons/angel-wings.png", "icons/angler-fish.png", "icons/angry-eyes.png", "icons/angular-spider.png", "icons/animal-hide.png", "icons/animal-skull.png", "icons/ankh.png", "icons/annexation.png", "icons/antarctica.png", "icons/anthem.png", "icons/antibody.png", "icons/anticlockwise-rotation.png", "icons/anubis.png", "icons/anvil-impact.png", "icons/anvil.png", "icons/apc.png", "icons/apothecary.png", "icons/apple-maggot.png", "icons/apple-seeds.png", "icons/aquarium.png", "icons/aquarius.png", "icons/aqueduct.png", "icons/arabic-door.png", "icons/arc-triomphe.png", "icons/archer.png", "icons/archery-target.png", "icons/architect-mask.png", "icons/arcing-bolt.png", "icons/arena.png", "icons/aries.png", "icons/arm.png", "icons/armadillo-tail.png", "icons/armadillo.png", "icons/armor-vest.png", "icons/armored-pants.png", "icons/armoured-shell.png", "icons/arrest.png", "icons/arrow-cluster.png", "icons/arrow-cursor.png", "icons/arrow-dunk.png", "icons/arrow-flights.png", "icons/arrow-scope.png", "icons/arrowed.png", "icons/arrowhead.png", "icons/arrows-shield.png", "icons/arson.png", "icons/artificial-hive.png", "icons/artificial-intelligence.png", "icons/artillery-shell.png", "icons/asian-lantern.png", "icons/aspergillum.png", "icons/assassin-pocket.png", "icons/asteroid.png", "icons/astronaut-helmet.png", "icons/at-sea.png", "icons/atlas.png", "icons/atom.png", "icons/atomic-slashes.png", "icons/attached-shield.png", "icons/aubergine.png", "icons/audio-cassette.png", "icons/aura.png", "icons/australia.png", "icons/auto-repair.png", "icons/autogun.png", "icons/automatic-sas.png", "icons/awareness.png", "icons/axe-in-stump.png", "icons/axe-swing.png", "icons/baby-face.png", "icons/babyfoot-players.png", "icons/back-forth.png", "icons/back-pain.png", "icons/backbone-shell.png", "icons/backgammon.png", "icons/backpack.png", "icons/backstab.png", "icons/backup.png", "icons/backward-time.png", "icons/bad-breath.png", "icons/bad-gnome.png", "icons/balaclava.png", "icons/balkenkreuz.png", "icons/ball-glow.png", "icons/ball-heart.png", "icons/ballista.png", "icons/balloons.png", "icons/banana-peel.png", "icons/banana.png", "icons/bandage-roll.png", "icons/bandaged.png", "icons/banging-gavel.png", "icons/bank.png", "icons/banknote.png", "icons/barbarian.png", "icons/barbed-arrow.png", "icons/barbed-coil.png", "icons/barbed-nails.png", "icons/barbed-spear.png", "icons/barbed-star.png", "icons/barbed-sun.png", "icons/barbed-wire.png", "icons/barbute.png", "icons/barefoot.png", "icons/barn.png", "icons/barracks-tent.png", "icons/barracks.png", "icons/barrel.png", "icons/barricade.png", "icons/barrier.png", "icons/baseball-bat.png", "icons/basketball-ball.png", "icons/basketball-basket.png", "icons/bat-blade.png", "icons/bat-wing.png", "icons/bat.png", "icons/baton.png", "icons/battered-axe.png", "icons/batteries.png", "icons/battery-0.png", "icons/battery-100.png", "icons/battery-25.png", "icons/battery-50.png", "icons/battery-75.png", "icons/battery-minus.png", "icons/battery-pack-alt.png", "icons/battery-pack.png", "icons/battery-plus.png", "icons/battle-axe.png", "icons/battle-gear.png", "icons/battle-mech.png", "icons/battleship.png", "icons/batwing-emblem.png", "icons/bayonet.png", "icons/beach-ball.png", "icons/beam-satellite.png", "icons/beam-wake.png", "icons/beams-aura.png", "icons/beanstalk.png", "icons/bear-face.png", "icons/bear-head.png", "icons/beard.png", "icons/beast-eye.png", "icons/bed-lamp.png", "icons/bed.png", "icons/bee.png", "icons/beech.png", "icons/beehive.png", "icons/beer-bottle.png", "icons/beer-stein.png", "icons/beet.png", "icons/beetle-shell.png", "icons/behold.png", "icons/belgium.png", "icons/bell-shield.png", "icons/belt-armor.png", "icons/belt-buckles.png", "icons/belt.png", "icons/bestial-fangs.png", "icons/beveled-star.png", "icons/biceps.png", "icons/big-diamond-ring.png", "icons/big-egg.png", "icons/big-gear.png", "icons/big-wave.png", "icons/bindle.png", "icons/binoculars.png", "icons/biohazard.png", "icons/biplane.png", "icons/bird-claw.png", "icons/bird-limb.png", "icons/bird-mask.png", "icons/bird-twitter.png", "icons/black-bar.png", "icons/black-belt.png", "icons/black-book.png", "icons/black-cat.png", "icons/black-flag.png", "icons/black-hand-shield.png", "icons/black-hole-bolas.png", "icons/black-knight-helm.png", "icons/black-sea.png", "icons/blackball.png", "icons/blackcurrant.png", "icons/blacksmith.png", "icons/blade-bite.png", "icons/blade-drag.png", "icons/blade-fall.png", "icons/blast.png", "icons/blaster.png", "icons/bleeding-eye.png", "icons/bleeding-heart.png", "icons/bleeding-wound.png", "icons/blindfold.png", "icons/block-house.png", "icons/blood.png", "icons/bloody-stash.png", "icons/bloody-sword.png", "icons/blunderbuss.png", "icons/bo.png", "icons/boar-ensign.png", "icons/boar-tusks.png", "icons/boat-propeller.png", "icons/body-balance.png", "icons/body-height.png", "icons/body-swapping.png", "icons/boiling-bubbles.png", "icons/bolas.png", "icons/bolivia.png", "icons/bolt-cutter.png", "icons/bolt-eye.png", "icons/bolt-saw.png", "icons/bolt-shield.png", "icons/bolt-spell-cast.png", "icons/bolter-gun.png", "icons/bomber.png", "icons/bombing-run.png", "icons/bone-gnawer.png", "icons/bone-knife.png", "icons/bonsai-tree.png", "icons/book-aura.png", "icons/book-cover.png", "icons/book-pile.png", "icons/book-storm.png", "icons/bookmark.png", "icons/bookmarklet.png", "icons/bookshelf.png", "icons/boomerang-sun.png", "icons/boomerang.png", "icons/boot-prints.png", "icons/boot-stomp.png", "icons/boots.png", "icons/bordered-shield.png", "icons/boss-key.png", "icons/bottle-cap.png", "icons/bottle-vapors.png", "icons/bottled-bolt.png", "icons/bottom-right-3d-arrow.png", "icons/boulder-dash.png", "icons/bouncing-sword.png", "icons/bow-arrow.png", "icons/bow-tie-ribbon.png", "icons/bow-tie.png", "icons/bowels.png", "icons/bowen-knot.png", "icons/bowie-knife.png", "icons/bowl-spiral.png", "icons/bowling-pin.png", "icons/bowling-propulsion.png", "icons/bowling-strike.png", "icons/bowman.png", "icons/box-cutter.png", "icons/box-trap.png", "icons/boxing-glove-surprise.png", "icons/boxing-glove.png", "icons/bracer.png", "icons/bracers.png", "icons/brain-freeze.png", "icons/brain-stem.png", "icons/brain.png", "icons/brainstorm.png", "icons/branch-arrow.png", "icons/brandy-bottle.png", "icons/brass-eye.png", "icons/brass-knuckles.png", "icons/brazil-flag.png", "icons/bread.png", "icons/breaking-chain.png", "icons/breastplate.png", "icons/brick-pile.png", "icons/brick-wall.png", "icons/bridge.png", "icons/briefcase.png", "icons/bright-explosion.png", "icons/broad-dagger.png", "icons/broadhead-arrow.png", "icons/broadsword.png", "icons/brodie-helmet.png", "icons/broken-bone.png", "icons/broken-bottle.png", "icons/broken-heart-zone.png", "icons/broken-heart.png", "icons/broken-pottery.png", "icons/broken-ribbon.png", "icons/broken-shield.png", "icons/broken-skull.png", "icons/broken-tablet.png", "icons/broken-wall.png", "icons/broom.png", "icons/brutal-helm.png", "icons/bubble-field.png", "icons/bubbles.png", "icons/bubbling-beam.png", "icons/bubbling-bowl.png", "icons/bubbling-flask.png", "icons/bud.png", "icons/bugle-call.png", "icons/bulb.png", "icons/bull-horns.png", "icons/bull.png", "icons/bulldozer.png", "icons/bullets.png", "icons/bullseye.png", "icons/bundle-grenade.png", "icons/bunker-assault.png", "icons/bunker.png", "icons/buoy.png", "icons/burn.png", "icons/burning-blobs.png", "icons/burning-book.png", "icons/burning-dot.png", "icons/burning-embers.png", "icons/burning-eye.png", "icons/burning-meteor.png", "icons/burning-passion.png", "icons/burning-round-shot.png", "icons/burning-skull.png", "icons/burning-tree.png", "icons/burst-blob.png", "icons/bus.png", "icons/butterfly-knife.png", "icons/butterfly-warning.png", "icons/butterfly.png", "icons/button-finger.png", "icons/buy-card.png", "icons/byzantin-temple.png", "icons/c96.png", "icons/cactus.png", "icons/cadillac-helm.png", "icons/caduceus.png", "icons/caesar.png", "icons/cage.png", "icons/caged-ball.png", "icons/cake-slice.png", "icons/calavera.png", "icons/caldera.png", "icons/calendar.png", "icons/caltrops.png", "icons/camargue-cross.png", "icons/cambodia.png", "icons/camel-head.png", "icons/campfire.png", "icons/camping-tent.png", "icons/cancel.png", "icons/cancer.png", "icons/candle-flame.png", "icons/candle-holder.png", "icons/candle-light.png", "icons/candle-skull.png", "icons/candlebright.png", "icons/candles.png", "icons/candlestick-phone.png", "icons/candy-canes.png", "icons/cannister.png", "icons/cannon-ball.png", "icons/cannon-shot.png", "icons/cannon.png", "icons/canoe.png", "icons/cape.png", "icons/capitol.png", "icons/capricorn.png", "icons/car-key.png", "icons/car-wheel.png", "icons/caravan.png", "icons/card-10-clubs.png", "icons/card-10-diamonds.png", "icons/card-10-hearts.png", "icons/card-10-spades.png", "icons/card-2-clubs.png", "icons/card-2-diamonds.png", "icons/card-2-hearts.png", "icons/card-2-spades.png", "icons/card-3-clubs.png", "icons/card-3-diamonds.png", "icons/card-3-hearts.png", "icons/card-3-spades.png", "icons/card-4-clubs.png", "icons/card-4-diamonds.png", "icons/card-4-hearts.png", "icons/card-4-spades.png", "icons/card-5-clubs.png", "icons/card-5-diamonds.png", "icons/card-5-hearts.png", "icons/card-5-spades.png", "icons/card-6-clubs.png", "icons/card-6-diamonds.png", "icons/card-6-hearts.png", "icons/card-6-spades.png", "icons/card-7-clubs.png", "icons/card-7-diamonds.png", "icons/card-7-hearts.png", "icons/card-7-spades.png", "icons/card-8-clubs.png", "icons/card-8-diamonds.png", "icons/card-8-hearts.png", "icons/card-8-spades.png", "icons/card-9-clubs.png", "icons/card-9-diamonds.png", "icons/card-9-hearts.png", "icons/card-9-spades.png", "icons/card-ace-clubs.png", "icons/card-ace-diamonds.png", "icons/card-ace-hearts.png", "icons/card-ace-spades.png", "icons/card-burn.png", "icons/card-discard.png", "icons/card-draw.png", "icons/card-exchange.png", "icons/card-jack-clubs.png", "icons/card-jack-diamonds.png", "icons/card-jack-hearts.png", "icons/card-jack-spades.png", "icons/card-joker.png", "icons/card-king-clubs.png", "icons/card-king-diamonds.png", "icons/card-king-hearts.png", "icons/card-king-spades.png", "icons/card-pick.png", "icons/card-pickup.png", "icons/card-play.png", "icons/card-queen-clubs.png", "icons/card-queen-diamonds.png", "icons/card-queen-hearts.png", "icons/card-queen-spades.png", "icons/card-random.png", "icons/cardboard-box.png", "icons/cargo-crane.png", "icons/carillon.png", "icons/carnival-mask.png", "icons/carnivore-mouth.png", "icons/carnivorous-plant.png", "icons/carnyx.png", "icons/carpet-bombing.png", "icons/carrier.png", "icons/carrion.png", "icons/carrot.png", "icons/cartwheel.png", "icons/cash.png", "icons/castle-ruins.png", "icons/castle.png", "icons/catapult.png", "icons/catch.png", "icons/cauldron.png", "icons/cavalry.png", "icons/cave-entrance.png", "icons/cctv-camera.png", "icons/ceiling-barnacle.png", "icons/ceiling-light.png", "icons/celebration-fire.png", "icons/cement-shoes.png", "icons/centipede.png", "icons/chain-lightning.png", "icons/chain-mail.png", "icons/chained-arrow-heads.png", "icons/chained-heart.png", "icons/chaingun.png", "icons/chainsaw.png", "icons/chalice-drops.png", "icons/chalk-outline-murder.png", "icons/chameleon-glyph.png", "icons/charged-arrow.png", "icons/charging-bull.png", "icons/chariot.png", "icons/charm.png", "icons/chart.png", "icons/checkbox-tree.png", "icons/checked-shield.png", "icons/checkered-diamond.png", "icons/checkered-flag.png", "icons/checklist.png", "icons/cheerful.png", "icons/cheese-wedge.png", "icons/chef-toque.png", "icons/chemical-arrow.png", "icons/chemical-bolt.png", "icons/chemical-drop.png", "icons/chemical-tank.png", "icons/cherish.png", "icons/cherry.png", "icons/chess-bishop.png", "icons/chess-king.png", "icons/chess-knight.png", "icons/chess-pawn.png", "icons/chess-queen.png", "icons/chess-rook.png", "icons/chest-armor.png", "icons/chest.png", "icons/chewed-heart.png", "icons/chewed-skull.png", "icons/chicken-leg.png", "icons/chicken-oven.png", "icons/chicken.png", "icons/chili-pepper.png", "icons/chimney.png", "icons/chips-bag.png", "icons/chocolate-bar.png", "icons/chopped-skull.png", "icons/church.png", "icons/cigar.png", "icons/cigarette.png", "icons/circle-cage.png", "icons/circle-claws.png", "icons/circle-sparks.png", "icons/circuitry.png", "icons/circular-saw.png", "icons/circular-sawblade.png", "icons/city-car.png", "icons/clapperboard.png", "icons/claw-hammer.png", "icons/claw-slashes.png", "icons/claw-string.png", "icons/claw.png", "icons/click.png", "icons/cloak-dagger.png", "icons/cloak.png", "icons/clockwise-rotation.png", "icons/clockwork.png", "icons/closed-barbute.png", "icons/closed-doors.png", "icons/cloud-ring.png", "icons/cloudy-fork.png", "icons/clout.png", "icons/clover-spiked.png", "icons/clover.png", "icons/clownfish.png", "icons/clubs.png", "icons/cluster-bomb.png", "icons/coal-wagon.png", "icons/cobra.png", "icons/cobweb.png", "icons/coconuts.png", "icons/coffee-beans.png", "icons/coffee-cup.png", "icons/coffee-mug.png", "icons/coffin.png", "icons/cog-lock.png", "icons/cog.png", "icons/cogsplosion.png", "icons/coiling-curl.png", "icons/coins.png", "icons/cold-heart.png", "icons/coliseum.png", "icons/colombia.png", "icons/colombian-statue.png", "icons/colt-m1911.png", "icons/coma.png", "icons/comb.png", "icons/combination-lock.png", "icons/comet-spark.png", "icons/compact-disc.png", "icons/companion-cube.png", "icons/compass.png", "icons/computer-fan.png", "icons/computing.png", "icons/concentration-orb.png", "icons/concentric-crescents.png", "icons/concrete-bag.png", "icons/condor-emblem.png", "icons/condylura-skull.png", "icons/confrontation.png", "icons/congress.png", "icons/conqueror.png", "icons/console-controller.png", "icons/contract.png", "icons/conversation.png", "icons/convict.png", "icons/convince.png", "icons/cooking-pot.png", "icons/cool-spices.png", "icons/corked-tube.png", "icons/corn.png", "icons/corner-explosion.png", "icons/corner-flag.png", "icons/cornucopia.png", "icons/coronation.png", "icons/corporal.png", "icons/corset.png", "icons/cotton-flower.png", "icons/covered-jar.png", "icons/cow.png", "icons/cowboy-boot.png", "icons/cowboy-holster.png", "icons/cowled.png", "icons/crab-claw.png", "icons/crab.png", "icons/cracked-alien-skull.png", "icons/cracked-ball-dunk.png", "icons/cracked-disc.png", "icons/cracked-glass.png", "icons/cracked-helm.png", "icons/cracked-mask.png", "icons/cracked-saber.png", "icons/cracked-shield.png", "icons/crags.png", "icons/crane.png", "icons/crenulated-shield.png", "icons/crescent-blade.png", "icons/crested-helmet.png", "icons/cricket.png", "icons/crime-scene-tape.png", "icons/croc-jaws.png", "icons/croc-sword.png", "icons/croissant.png", "icons/croissants-pupil.png", "icons/cross-flare.png", "icons/cross-mark.png", "icons/cross-shield.png", "icons/crossbow.png", "icons/crossed-air-flows.png", "icons/crossed-axes.png", "icons/crossed-bones.png", "icons/crossed-chains.png", "icons/crossed-claws.png", "icons/crossed-pistols.png", "icons/crossed-sabres.png", "icons/crossed-slashes.png", "icons/crossed-swords.png", "icons/crosshair-arrow.png", "icons/crosshair.png", "icons/crow-dive.png", "icons/crow-nest.png", "icons/crowbar.png", "icons/crown-coin.png", "icons/crown-of-thorns.png", "icons/crown.png", "icons/crowned-explosion.png", "icons/crowned-heart.png", "icons/crowned-skull.png", "icons/crucifix.png", "icons/cruiser.png", "icons/crumbling-ball.png", "icons/crush.png", "icons/cryo-chamber.png", "icons/crystal-ball.png", "icons/crystal-bars.png", "icons/crystal-cluster.png", "icons/crystal-eye.png", "icons/crystal-growth.png", "icons/crystal-shine.png", "icons/crystal-shrine.png", "icons/crystal-wand.png", "icons/crystalize.png", "icons/cube.png", "icons/cubeforce.png", "icons/cubes.png", "icons/cultist.png", "icons/cupcake.png", "icons/cupidon-arrow.png", "icons/curled-leaf.png", "icons/curled-tentacle.png", "icons/curling-vines.png", "icons/curly-mask.png", "icons/curly-wing.png", "icons/cursed-star.png", "icons/curvy-knife.png", "icons/cut-diamond.png", "icons/cut-lemon.png", "icons/cut-palm.png", "icons/cyber-eye.png", "icons/cyborg-face.png", "icons/cycle.png", "icons/cyclops.png", "icons/cz-skorpion.png", "icons/d10.png", "icons/d12.png", "icons/d4.png", "icons/daemon-skull.png", "icons/dagger-rose.png", "icons/daggers.png", "icons/daisy.png", "icons/damaged-house.png", "icons/dark-squad.png", "icons/dart.png", "icons/database.png", "icons/dead-eye.png", "icons/dead-head.png", "icons/dead-wood.png", "icons/deadly-strike.png", "icons/death-juice.png", "icons/death-note.png", "icons/death-skull.png", "icons/death-zone.png", "icons/deathcab.png", "icons/decapitation.png", "icons/defense-satellite.png", "icons/defensive-wall.png", "icons/defibrilate.png", "icons/delighted.png", "icons/demolish.png", "icons/dervish-swords.png", "icons/desert-eagle.png", "icons/desert-skull.png", "icons/desert.png", "icons/deshret-red-crown.png", "icons/desk-lamp.png", "icons/desk.png", "icons/despair.png", "icons/detour.png", "icons/dew.png", "icons/diablo-skull.png", "icons/diagram.png", "icons/diamond-hard.png", "icons/diamond-hilt.png", "icons/diamond-ring.png", "icons/diamonds-smile.png", "icons/diamonds.png", "icons/dice-eight-faces-eight.png", "icons/dice-six-faces-five.png", "icons/dice-six-faces-four.png", "icons/dice-six-faces-one.png", "icons/dice-six-faces-six.png", "icons/dice-six-faces-three.png", "icons/dice-six-faces-two.png", "icons/dice-twenty-faces-twenty.png", "icons/dig-dug.png", "icons/dig-hole.png", "icons/digital-trace.png", "icons/dimetrodon.png", "icons/dinosaur-bones.png", "icons/dinosaur-egg.png", "icons/dinosaur-rex.png", "icons/diplodocus.png", "icons/diploma.png", "icons/direction-sign.png", "icons/direction-signs.png", "icons/director-chair.png", "icons/direwolf.png", "icons/discussion.png", "icons/disintegrate.png", "icons/distraction.png", "icons/divergence.png", "icons/divert.png", "icons/divided-spiral.png", "icons/divided-square.png", "icons/diving-dagger.png", "icons/diving-helmet.png", "icons/dna1.png", "icons/dna2.png", "icons/doctor-face.png", "icons/dodge.png", "icons/dodging.png", "icons/dog-bowl.png", "icons/dolphin.png", "icons/domino-mask.png", "icons/donkey.png", "icons/dorsal-scales.png", "icons/double-diaphragm.png", "icons/double-dragon.png", "icons/double-face-mask.png", "icons/double-quaver.png", "icons/double-ringed-orb.png", "icons/double-shot.png", "icons/doubled.png", "icons/dough-roller.png", "icons/dove.png", "icons/dozen.png", "icons/dragon-balls.png", "icons/dragon-breath.png", "icons/dragon-head.png", "icons/dragon-spiral.png", "icons/dragonfly.png", "icons/drakkar-dragon.png", "icons/drakkar.png", "icons/drama-masks.png", "icons/drawbridge.png", "icons/dread-skull.png", "icons/dread.png", "icons/dreadnought.png", "icons/dream-catcher.png", "icons/dress.png", "icons/drill.png", "icons/drink-me.png", "icons/drinking.png", "icons/dripping-blade.png", "icons/dripping-goo.png", "icons/dripping-honey.png", "icons/dripping-knife.png", "icons/dripping-star.png", "icons/dripping-stone.png", "icons/dripping-sword.png", "icons/dripping-tube.png", "icons/drop-weapon.png", "icons/drop.png", "icons/droplet-splash.png", "icons/droplets.png", "icons/drowning.png", "icons/drum.png", "icons/duality-mask.png", "icons/duality.png", "icons/duck.png", "icons/duel.png", "icons/dunce-cap.png", "icons/dungeon-gate.png", "icons/dungeon-light.png", "icons/dust-cloud.png", "icons/dutch-bike.png", "icons/dwarf-face.png", "icons/dwarf-helmet.png", "icons/dwarf-king.png", "icons/dwennimmen.png", "icons/dynamite.png", "icons/eagle-emblem.png", "icons/earth-africa-europe.png", "icons/earth-america.png", "icons/earth-asia-oceania.png", "icons/earth-crack.png", "icons/earth-spit.png", "icons/earth-worm.png", "icons/earwig.png", "icons/easter-egg.png", "icons/eating-pelican.png", "icons/eating.png", "icons/echo-ripples.png", "icons/eclipse-flare.png", "icons/eclipse-saw.png", "icons/eclipse.png", "icons/ecology.png", "icons/edge-crack.png", "icons/edged-shield.png", "icons/eel.png", "icons/egg-clutch.png", "icons/egg-defense.png", "icons/egg-pod.png", "icons/egypt.png", "icons/egyptian-pyramids.png", "icons/egyptian-sphinx.png", "icons/egyptian-walk.png", "icons/eight-ball.png", "icons/elderberry.png", "icons/electric-whip.png", "icons/electric.png", "icons/electrical-crescent.png", "icons/elephant-head.png", "icons/elephant.png", "icons/elf-ear.png", "icons/elf-helmet.png", "icons/ember-shot.png", "icons/embrassed-energy.png", "icons/embryo.png", "icons/emerald.png", "icons/empty-chessboard.png", "icons/empty-hourglass.png", "icons/empty-metal-bucket-handle.png", "icons/empty-metal-bucket.png", "icons/empty-wood-bucket-handle.png", "icons/empty-wood-bucket.png", "icons/encirclement.png", "icons/energise.png", "icons/energy-arrow.png", "icons/energy-breath.png", "icons/energy-shield.png", "icons/energy-sword.png", "icons/energy-tank.png", "icons/engagement-ring.png", "icons/enlightenment.png", "icons/enrage.png", "icons/ent-mouth.png", "icons/entangled-typhoon.png", "icons/entry-door.png", "icons/envelope.png", "icons/erlenmeyer.png", "icons/ermine.png", "icons/eruption.png", "icons/eskimo.png", "icons/eternal-love.png", "icons/european-flag.png", "icons/evil-bat.png", "icons/evil-book.png", "icons/evil-bud.png", "icons/evil-comet.png", "icons/evil-eyes.png", "icons/evil-fork.png", "icons/evil-hand.png", "icons/evil-minion.png", "icons/evil-moon.png", "icons/evil-tower.png", "icons/evil-tree.png", "icons/evil-wings.png", "icons/exit-door.png", "icons/expand.png", "icons/expanded-rays.png", "icons/expander.png", "icons/expense.png", "icons/explosion-rays.png", "icons/explosive-materials.png", "icons/explosive-meeting.png", "icons/extra-lucid.png", "icons/extraction-orb.png", "icons/eye-of-horus.png", "icons/eye-shield.png", "icons/eye-target.png", "icons/eyeball.png", "icons/eyedropper.png", "icons/eyestalk.png", "icons/f-clef.png", "icons/f1-car.png", "icons/face-to-face.png", "icons/factory-arm.png", "icons/factory.png", "icons/fairy-wand.png", "icons/fairy.png", "icons/falcon-moon.png", "icons/fall-down.png", "icons/falling-blob.png", "icons/falling-boulder.png", "icons/falling-eye.png", "icons/falling-leaf.png", "icons/falling-ovoid.png", "icons/falling-rocks.png", "icons/falling.png", "icons/fallout-shelter.png", "icons/famas.png", "icons/family-house.png", "icons/fanged-skull.png", "icons/fangs-circle.png", "icons/fangs.png", "icons/farm-tractor.png", "icons/farmer.png", "icons/fast-arrow.png", "icons/fat.png", "icons/feather-wound.png", "icons/feather.png", "icons/feathered-wing.png", "icons/fedora.png", "icons/feline.png", "icons/female-vampire.png", "icons/female.png", "icons/fencer.png", "icons/fetus.png", "icons/fez.png", "icons/field-gun.png", "icons/field.png", "icons/files.png", "icons/film-projector.png", "icons/film-spool.png", "icons/film-strip.png", "icons/finger-print.png", "icons/fingers-crossed.png", "icons/fire-ace.png", "icons/fire-axe.png", "icons/fire-bomb.png", "icons/fire-bottle.png", "icons/fire-bowl.png", "icons/fire-breath.png", "icons/fire-dash.png", "icons/fire-extinguisher.png", "icons/fire-flower.png", "icons/fire-punch.png", "icons/fire-ray.png", "icons/fire-ring.png", "icons/fire-shield.png", "icons/fire-silhouette.png", "icons/fire-tail.png", "icons/fire-wave.png", "icons/fire-zone.png", "icons/fire.png", "icons/fireball.png", "icons/fireflake.png", "icons/firework-rocket.png", "icons/first-aid-kit.png", "icons/fish-cooked.png", "icons/fish-corpse.png", "icons/fish-monster.png", "icons/fish-smoking.png", "icons/fishbone.png", "icons/fishhook-fork.png", "icons/fishing-boat.png", "icons/fishing-hook.png", "icons/fishing-net.png", "icons/fishing-pole.png", "icons/fishing-spoon.png", "icons/fishing.png", "icons/fission.png", "icons/fist.png", "icons/fizzing-flask.png", "icons/flail.png", "icons/flake.png", "icons/flame-claws.png", "icons/flame-spin.png", "icons/flame-tunnel.png", "icons/flame.png", "icons/flamed-leaf.png", "icons/flamer.png", "icons/flaming-arrow.png", "icons/flaming-claw.png", "icons/flaming-sheet.png", "icons/flaming-trident.png", "icons/flamingo.png", "icons/flanged-mace.png", "icons/flash-grenade.png", "icons/flashlight.png", "icons/flat-hammer.png", "icons/flat-star.png", "icons/flat-tire.png", "icons/flatbed-covered.png", "icons/flatbed.png", "icons/fleshy-mass.png", "icons/fleur-de-lys.png", "icons/flexible-star.png", "icons/flip-flops.png", "icons/floating-crystal.png", "icons/flood.png", "icons/floor-hatch.png", "icons/floor-polisher.png", "icons/flower-pot.png", "icons/flower-star.png", "icons/flower-twirl.png", "icons/flowers.png", "icons/fluffy-cloud.png", "icons/fluffy-flame.png", "icons/fluffy-swirl.png", "icons/fluffy-trefoil.png", "icons/fluffy-wing.png", "icons/fly.png", "icons/flying-dagger.png", "icons/flying-flag.png", "icons/flying-fox.png", "icons/flying-shuriken.png", "icons/flying-target.png", "icons/flying-trout.png", "icons/fn-fal.png", "icons/foam.png", "icons/foamy-disc.png", "icons/focused-lightning.png", "icons/folded-paper.png", "icons/fomorian.png", "icons/food-chain.png", "icons/foot-plaster.png", "icons/foot-trip.png", "icons/footprint.png", "icons/footsteps.png", "icons/forearm.png", "icons/forest.png", "icons/forklift.png", "icons/forward-field.png", "icons/forward-sun.png", "icons/fossil.png", "icons/fountain-pen.png", "icons/fountain.png", "icons/fox-head.png", "icons/fox-tail.png", "icons/fragmented-meteor.png", "icons/fragmented-sword.png", "icons/fragrance.png", "icons/frankenstein-creature.png", "icons/frayed-arrow.png", "icons/freedom-dove.png", "icons/freemasonry.png", "icons/fried-eggs.png", "icons/fried-fish.png", "icons/frisbee.png", "icons/frog-prince.png", "icons/frog.png", "icons/front-teeth.png", "icons/frontal-lobe.png", "icons/frostfire.png", "icons/frozen-arrow.png", "icons/frozen-block.png", "icons/frozen-orb.png", "icons/fruit-bowl.png", "icons/fruit-tree.png", "icons/fruiting.png", "icons/fuel-tank.png", "icons/fuji.png", "icons/fulguro-punch.png", "icons/full-folder.png", "icons/full-metal-bucket-handle.png", "icons/full-metal-bucket.png", "icons/full-motorcycle-helmet.png", "icons/full-wood-bucket-handle.png", "icons/full-wood-bucket.png", "icons/funnel.png", "icons/fur-boot.png", "icons/g-clef.png", "icons/galea.png", "icons/galleon.png", "icons/game-console.png", "icons/gamepad-cross.png", "icons/gamepad.png", "icons/gargoyle.png", "icons/garlic.png", "icons/gas-mask.png", "icons/gas-pump.png", "icons/gate.png", "icons/gauls-helm.png", "icons/gauntlet.png", "icons/gavel.png", "icons/gaze.png", "icons/gear-hammer.png", "icons/gears.png", "icons/gecko.png", "icons/gem-chain.png", "icons/gem-necklace.png", "icons/gem-pendant.png", "icons/gemini.png", "icons/gems.png", "icons/ghost-ally.png", "icons/ghost.png", "icons/giant-squid.png", "icons/giant.png", "icons/gibbet.png", "icons/gift-of-knowledge.png", "icons/gift-trap.png", "icons/gingerbread-man.png", "icons/gladius.png", "icons/glass-celebration.png", "icons/glass-heart.png", "icons/glass-shot.png", "icons/glider.png", "icons/globe-ring.png", "icons/globe.png", "icons/glock.png", "icons/gloop.png", "icons/gloves.png", "icons/glowing-hands.png", "icons/gluttonous-smile.png", "icons/gluttony.png", "icons/goat.png", "icons/goblin-camp.png", "icons/goblin-head.png", "icons/gold-bar.png", "icons/gold-mine.png", "icons/gold-nuggets.png", "icons/gold-scarab.png", "icons/gold-shell.png", "icons/gold-stack.png", "icons/golem-head.png", "icons/golf-flag.png", "icons/goo-explosion.png", "icons/goo-skull.png", "icons/goo-spurt.png", "icons/gooey-daemon.png", "icons/gooey-eyed-sun.png", "icons/gooey-impact.png", "icons/gooey-molecule.png", "icons/gooey-sword.png", "icons/goose.png", "icons/gorilla.png", "icons/gothic-cross.png", "icons/gps.png", "icons/grab.png", "icons/graduate-cap.png", "icons/grain.png", "icons/grapes.png", "icons/grapple.png", "icons/grasping-claws.png", "icons/grass.png", "icons/graveyard.png", "icons/grease-trap.png", "icons/great-war-tank.png", "icons/greaves.png", "icons/greek-sphinx.png", "icons/greek-temple.png", "icons/greenhouse.png", "icons/grenade.png", "icons/griffin-symbol.png", "icons/grim-reaper.png", "icons/ground-sprout.png", "icons/groundbreaker.png", "icons/grouped-drops.png", "icons/guarded-tower.png", "icons/guards.png", "icons/guillotine.png", "icons/guitar.png", "icons/gun-rose.png", "icons/gunshot.png", "icons/h2o.png", "icons/habitat-dome.png", "icons/hair-strands.png", "icons/halberd-shuriken.png", "icons/halberd.png", "icons/half-body-crawling.png", "icons/half-heart.png", "icons/half-tornado.png", "icons/halt.png", "icons/ham-shank.png", "icons/hamburger-menu.png", "icons/hamburger.png", "icons/hammer-drop.png", "icons/hammer-nails.png", "icons/hammer-sickle.png", "icons/hand-bag.png", "icons/hand-grip.png", "icons/hand-of-god.png", "icons/hand-ok.png", "icons/hand-saw.png", "icons/hand.png", "icons/handcuffed.png", "icons/handcuffs.png", "icons/handheld-fan.png", "icons/hang-glider.png", "icons/hanger.png", "icons/hanging-spider.png", "icons/happy-skull.png", "icons/harpoon-chain.png", "icons/harpoon-trident.png", "icons/harpy.png", "icons/harry-potter-skull.png", "icons/hasty-grave.png", "icons/hatchets.png", "icons/haunting.png", "icons/hawk-emblem.png", "icons/hazard-sign.png", "icons/hazmat-suit.png", "icons/head-shot.png", "icons/headband-knot.png", "icons/headphones.png", "icons/headshot.png", "icons/health-decrease.png", "icons/health-increase.png", "icons/health-normal.png", "icons/hearing-disabled.png", "icons/heart-beats.png", "icons/heart-bottle.png", "icons/heart-drop.png", "icons/heart-inside.png", "icons/heart-minus.png", "icons/heart-necklace.png", "icons/heart-organ.png", "icons/heart-plus.png", "icons/heart-tower.png", "icons/heart-wings.png", "icons/heartburn.png", "icons/hearts.png", "icons/heat-haze.png", "icons/heavy-arrow.png", "icons/heavy-fall.png", "icons/heavy-fighter.png", "icons/heavy-helm.png", "icons/heavy-lightning.png", "icons/heavy-rain.png", "icons/heavy-thorny-triskelion.png", "icons/heavy-timer.png", "icons/hedjet-white-crown.png", "icons/helicoprion.png", "icons/helicopter-tail.png", "icons/helicopter.png", "icons/hell-crosses.png", "icons/helmet-head-shot.png", "icons/helmet.png", "icons/help.png", "icons/hemp.png", "icons/heptagram.png", "icons/hexagonal-nut.png", "icons/hexes.png", "icons/hidden.png", "icons/hieroglyph-y.png", "icons/high-five.png", "icons/high-grass.png", "icons/high-heel.png", "icons/high-kick.png", "icons/high-shot.png", "icons/high-tide.png", "icons/hill-conquest.png", "icons/hill-fort.png", "icons/hills.png", "icons/histogram.png", "icons/hive.png", "icons/hobbit-dwelling.png", "icons/hockey.png", "icons/hole-ladder.png", "icons/holosphere.png", "icons/holy-grail.png", "icons/holy-hand-grenade.png", "icons/holy-oak.png", "icons/holy-symbol.png", "icons/holy-water.png", "icons/home-garage.png", "icons/honeycomb.png", "icons/honeypot.png", "icons/hood.png", "icons/hooded-assassin.png", "icons/hooded-figure.png", "icons/hoof.png", "icons/hops.png", "icons/horizontal-flip.png", "icons/horn-internal.png", "icons/horned-helm.png", "icons/horned-skull.png", "icons/horse-head.png", "icons/horseshoe.png", "icons/horus.png", "icons/hospital-cross.png", "icons/hot-meal.png", "icons/hot-spices.png", "icons/hot-surface.png", "icons/hound.png", "icons/hourglass.png", "icons/house.png", "icons/human-ear.png", "icons/hunting-horn.png", "icons/hydra-shot.png", "icons/hydra.png", "icons/hypersonic-bolt.png", "icons/hypersonic-melon.png", "icons/hypodermic-test.png", "icons/i-beam.png", "icons/i-brick.png", "icons/icarus.png", "icons/ice-bolt.png", "icons/ice-bomb.png", "icons/ice-cream-cone.png", "icons/ice-cube.png", "icons/ice-golem.png", "icons/ice-pop.png", "icons/ice-shield.png", "icons/ice-skate.png", "icons/ice-spear.png", "icons/ice-spell-cast.png", "icons/iceberg.png", "icons/icebergs.png", "icons/icicles-aura.png", "icons/icicles-fence.png", "icons/id-card.png", "icons/idea.png", "icons/ifrit.png", "icons/igloo.png", "icons/imbricated-arrows.png", "icons/imp-laugh.png", "icons/imp.png", "icons/impact-point.png", "icons/implosion.png", "icons/imprisoned.png", "icons/incense.png", "icons/incisors.png", "icons/incoming-rocket.png", "icons/indian-palace.png", "icons/infested-mass.png", "icons/infinity.png", "icons/info.png", "icons/injustice.png", "icons/ink-swirl.png", "icons/inner-self.png", "icons/insect-jaws.png", "icons/interdiction.png", "icons/interlaced-tentacles.png", "icons/interleaved-arrows.png", "icons/interleaved-claws.png", "icons/internal-injury.png", "icons/internal-organ.png", "icons/interstellar-path.png", "icons/inverted-dice-1.png", "icons/inverted-dice-2.png", "icons/inverted-dice-3.png", "icons/inverted-dice-4.png", "icons/inverted-dice-5.png", "icons/inverted-dice-6.png", "icons/invisible-face.png", "icons/invisible.png", "icons/ion-cannon-blast.png", "icons/ionic-column.png", "icons/iron-cross.png", "icons/iron-hulled-warship.png", "icons/iron-mask.png", "icons/island.png", "icons/italia.png", "icons/ivory-tusks.png", "icons/j-brick.png", "icons/jack-plug.png", "icons/james-bond-aperture.png", "icons/japan.png", "icons/jason-mask.png", "icons/jawbone.png", "icons/jeep.png", "icons/jellyfish.png", "icons/jerrycan.png", "icons/jester-hat.png", "icons/jet-fighter.png", "icons/jet-pack.png", "icons/jetpack.png", "icons/jewel-crown.png", "icons/jeweled-chalice.png", "icons/jigsaw-box.png", "icons/jigsaw-piece.png", "icons/join.png", "icons/joint.png", "icons/journey.png", "icons/joystick.png", "icons/jug.png", "icons/juggler.png", "icons/jump-across.png", "icons/jupiter.png", "icons/justice-star.png", "icons/kaleidoscope-pearls.png", "icons/katana.png", "icons/kevlar-vest.png", "icons/kevlar.png", "icons/key.png", "icons/keyboard.png", "icons/keyring.png", "icons/kimono.png", "icons/kindle.png", "icons/king-ju-mask.png", "icons/king.png", "icons/kitchen-knives.png", "icons/kite.png", "icons/kiwi-bird.png", "icons/klingon.png", "icons/knapsack.png", "icons/knee-pad.png", "icons/kneeling.png", "icons/knife-fork.png", "icons/knife-thrust.png", "icons/knockout.png", "icons/knot.png", "icons/kusarigama.png", "icons/l-brick.png", "icons/ladder.png", "icons/lamellar.png", "icons/lamprey-mouth.png", "icons/land-mine.png", "icons/lantern-flame.png", "icons/lantern.png", "icons/large-paint-brush.png", "icons/large-wound.png", "icons/laser-blast.png", "icons/laser-burst.png", "icons/laser-gun.png", "icons/laser-precision.png", "icons/laser-sparks.png", "icons/laser-turret.png", "icons/laser-warning.png", "icons/laserburn.png", "icons/lasso.png", "icons/latvia.png", "icons/laurel-crown.png", "icons/laurels-trophy.png", "icons/laurels.png", "icons/lava.png", "icons/law-star.png", "icons/layered-armor.png", "icons/lead-pipe.png", "icons/leaf-skeleton.png", "icons/leaf-swirl.png", "icons/leaky-skull.png", "icons/leather-boot.png", "icons/leather-vest.png", "icons/led.png", "icons/lee-enfield.png", "icons/leeching-worm.png", "icons/leg-armor.png", "icons/lemon.png", "icons/leo.png", "icons/letter-bomb.png", "icons/level-crossing.png", "icons/level-four-advanced.png", "icons/level-four.png", "icons/level-three-advanced.png", "icons/level-three.png", "icons/level-two-advanced.png", "icons/level-two.png", "icons/lever.png", "icons/liar.png", "icons/libra.png", "icons/license.txt", "icons/life-buoy.png", "icons/life-in-the-balance.png", "icons/life-support.png", "icons/life-tap.png", "icons/lift.png", "icons/light-backpack.png", "icons/light-bulb.png", "icons/light-fighter.png", "icons/light-sabers.png", "icons/light-thorny-triskelion.png", "icons/lighter.png", "icons/lighthouse.png", "icons/lightning-arc.png", "icons/lightning-bow.png", "icons/lightning-branches.png", "icons/lightning-dissipation.png", "icons/lightning-dome.png", "icons/lightning-electron.png", "icons/lightning-frequency.png", "icons/lightning-helix.png", "icons/lightning-mask.png", "icons/lightning-saber.png", "icons/lightning-shadow.png", "icons/lightning-shield.png", "icons/lightning-shout.png", "icons/lightning-slashes.png", "icons/lightning-spanner.png", "icons/lightning-storm.png", "icons/lightning-tear.png", "icons/lightning-tree.png", "icons/lightning-trio.png", "icons/lily-pads.png", "icons/linden-leaf.png", "icons/linked-rings.png", "icons/lion.png", "icons/lips.png", "icons/lipstick.png", "icons/lit-candelabra.png", "icons/liver.png", "icons/lizard-tongue.png", "icons/lizardman.png", "icons/load.png", "icons/lob-arrow.png", "icons/lock-picking.png", "icons/lock-spy.png", "icons/locked-chest.png", "icons/locked-fortress.png", "icons/locked-heart.png", "icons/lockpicks.png", "icons/log.png", "icons/logging.png", "icons/loincloth.png", "icons/long-antennae-bug.png", "icons/long-legged-spider.png", "icons/look-at.png", "icons/lost-limb.png", "icons/lotus-flower.png", "icons/lotus.png", "icons/love-howl.png", "icons/love-injection.png", "icons/love-letter.png", "icons/love-mystery.png", "icons/love-song.png", "icons/lovers.png", "icons/low-tide.png", "icons/luchador.png", "icons/lucifer-cannon.png", "icons/luger.png", "icons/lunar-module.png", "icons/lunar-wand.png", "icons/lungs.png", "icons/lynx-head.png", "icons/lyre.png", "icons/m3-grease-gun.png", "icons/mac-10.png", "icons/mace-head.png", "icons/machete.png", "icons/machine-gun.png", "icons/mad-scientist.png", "icons/maggot.png", "icons/magic-gate.png", "icons/magic-hat.png", "icons/magic-lamp.png", "icons/magic-palm.png", "icons/magic-portal.png", "icons/magic-potion.png", "icons/magic-shield.png", "icons/magic-swirl.png", "icons/magic-trident.png", "icons/magick-trick.png", "icons/magnet-blast.png", "icons/magnet.png", "icons/magnifying-glass.png", "icons/mail-shirt.png", "icons/mailbox.png", "icons/mailed-fist.png", "icons/male.png", "icons/manacles.png", "icons/mandrill-head.png", "icons/manta-ray.png", "icons/mantrap.png", "icons/manual-juicer.png", "icons/maple-leaf.png", "icons/marbles.png", "icons/marrow-drain.png", "icons/mars-curiosity.png", "icons/mars-pathfinder.png", "icons/martini.png", "icons/masked-spider.png", "icons/mass-driver.png", "icons/master-of-arms.png", "icons/match-head.png", "icons/match-tip.png", "icons/materials-science.png", "icons/matryoshka-dolls.png", "icons/mayan-pyramid.png", "icons/maze-cornea.png", "icons/maze-saw.png", "icons/maze.png", "icons/meal.png", "icons/meat-cleaver.png", "icons/meat-hook.png", "icons/meat.png", "icons/mecha-head.png", "icons/mecha-mask.png", "icons/mechanic-garage.png", "icons/mechanical-arm.png", "icons/medal-skull.png", "icons/medal.png", "icons/medallist.png", "icons/medical-pack-alt.png", "icons/medical-pack.png", "icons/medieval-gate.png", "icons/medieval-pavilion.png", "icons/meditation.png", "icons/medusa-head.png", "icons/meeple-group.png", "icons/meeple-king.png", "icons/meeple.png", "icons/megabot.png", "icons/megaphone.png", "icons/menhir.png", "icons/mermaid.png", "icons/mesh-ball.png", "icons/metal-bar.png", "icons/metal-disc.png", "icons/metal-hand.png", "icons/metal-plate.png", "icons/metal-scales.png", "icons/meteor-impact.png", "icons/metroid.png", "icons/mexico.png", "icons/microchip.png", "icons/microphone.png", "icons/microscope-lens.png", "icons/microscope.png", "icons/middle-arrow.png", "icons/midnight-claw.png", "icons/mighty-boosh.png", "icons/mighty-spanner.png", "icons/military-ambulance.png", "icons/military-fort.png", "icons/milk-carton.png", "icons/millenium-key.png", "icons/mine-explosion.png", "icons/mine-wagon.png", "icons/minefield.png", "icons/mineral-heart.png", "icons/minerals.png", "icons/mini-submarine.png", "icons/minigun.png", "icons/mining-helmet.png", "icons/mining.png", "icons/minions.png", "icons/minotaur.png", "icons/miracle-medecine.png", "icons/mirror-mirror.png", "icons/missile-launcher.png", "icons/missile-mech.png", "icons/missile-pod.png", "icons/missile-swarm.png", "icons/mite-alt.png", "icons/mite.png", "icons/modern-city.png", "icons/moebius-star.png", "icons/moebius-trefoil.png", "icons/moebius-triangle.png", "icons/molecule.png", "icons/molotov.png", "icons/mona-lisa.png", "icons/moncler-jacket.png", "icons/money-stack.png", "icons/monk-face.png", "icons/monkey-wrench.png", "icons/monkey.png", "icons/mono-wheel-robot.png", "icons/monster-grasp.png", "icons/moon-claws.png", "icons/moon.png", "icons/morbid-humour.png", "icons/morgue-feet.png", "icons/morph-ball.png", "icons/mortar.png", "icons/mountain-cave.png", "icons/mountains.png", "icons/mountaintop.png", "icons/mounted-knight.png", "icons/mouse.png", "icons/mouth-watering.png", "icons/move.png", "icons/movement-sensor.png", "icons/mp-40.png", "icons/mp5.png", "icons/mp5k.png", "icons/mucous-pillar.png", "icons/mug-shot.png", "icons/multiple-targets.png", "icons/mummy-head.png", "icons/muscle-fat.png", "icons/muscle-up.png", "icons/muscular-torso.png", "icons/mushroom-cloud.png", "icons/mushroom-gills.png", "icons/mushroom-house.png", "icons/mushroom.png", "icons/music-spell.png", "icons/musket.png", "icons/mute.png", "icons/nailed-foot.png", "icons/nailed-head.png", "icons/nails.png", "icons/nautilus-shell.png", "icons/neck-bite.png", "icons/necklace.png", "icons/needle-drill.png", "icons/needle-jaws.png", "icons/nefertiti.png", "icons/nested-eclipses.png", "icons/nested-hearts.png", "icons/nested-hexagons.png", "icons/new-born.png", "icons/newspaper.png", "icons/night-sky.png", "icons/night-vision.png", "icons/ninja-armor.png", "icons/ninja-head.png", "icons/ninja-heroic-stance.png", "icons/ninja-mask.png", "icons/ninja-star.png", "icons/ninja-velociraptor.png", "icons/nodular.png", "icons/noodle-ball.png", "icons/noodles.png", "icons/north-star-shuriken.png", "icons/nose-front.png", "icons/nose-side.png", "icons/nothing-to-say.png", "icons/nuclear-bomb.png", "icons/nuclear-plant.png", "icons/nuclear.png", "icons/nun-face.png", "icons/nunchaku.png", "icons/o-brick.png", "icons/oak-leaf.png", "icons/oak.png", "icons/oat.png", "icons/obelisk.png", "icons/observatory.png", "icons/ocarina.png", "icons/occupy.png", "icons/octogonal-eye.png", "icons/octoman.png", "icons/octopus.png", "icons/offshore-platform.png", "icons/ogre.png", "icons/oil-drum.png", "icons/oil-pump.png", "icons/oil-rig.png", "icons/oily-spiral.png", "icons/old-king.png", "icons/old-microphone.png", "icons/old-wagon.png", "icons/olive.png", "icons/omega.png", "icons/on-sight.png", "icons/on-target.png", "icons/one-eyed.png", "icons/oni.png", "icons/open-book.png", "icons/open-chest.png", "icons/open-folder.png", "icons/open-palm.png", "icons/open-treasure-chest.png", "icons/open-wound.png", "icons/opened-food-can.png", "icons/opening-shell.png", "icons/ophiuchus.png", "icons/oppidum.png", "icons/oppression.png", "icons/orange.png", "icons/orb-direction.png", "icons/orb-wand.png", "icons/orbit.png", "icons/orbital-rays.png", "icons/orbital.png", "icons/orc-head.png", "icons/ore.png", "icons/organigram.png", "icons/ouroboros.png", "icons/over-infinity.png", "icons/overdose.png", "icons/overdrive.png", "icons/overhead.png", "icons/overkill.png", "icons/overlord-helm.png", "icons/overmind.png", "icons/owl.png", "icons/oyster-pearl.png", "icons/p90.png", "icons/paddles.png", "icons/padlock.png", "icons/pagoda.png", "icons/paint-brush.png", "icons/paint-bucket.png", "icons/paint-roller.png", "icons/painted-pottery.png", "icons/palette.png", "icons/palisade.png", "icons/palm-tree.png", "icons/palm.png", "icons/pan-flute.png", "icons/panda.png", "icons/panzerfaust.png", "icons/paper-arrow.png", "icons/paper-bomb.png", "icons/paper-clip.png", "icons/paper-lantern.png", "icons/paper-plane.png", "icons/paper-tray.png", "icons/paper-windmill.png", "icons/paper.png", "icons/papers.png", "icons/papyrus.png", "icons/parachute.png", "icons/paranoia.png", "icons/parasaurolophus.png", "icons/parmecia.png", "icons/parrot-head.png", "icons/party-flags.png", "icons/path-tile.png", "icons/pauldrons.png", "icons/pause-button.png", "icons/paw-front.png", "icons/paw-heart.png", "icons/paw-print.png", "icons/paw.png", "icons/pawn.png", "icons/pawprint.png", "icons/pc.png", "icons/peaks.png", "icons/peanut.png", "icons/pear.png", "icons/pearl-necklace.png", "icons/pegasus.png", "icons/pelvis-bone.png", "icons/pencil-brush.png", "icons/pencil.png", "icons/pendulum-swing.png", "icons/penguin.png", "icons/pentacle.png", "icons/pentagram-rose.png", "icons/pentarrows-tornado.png", "icons/perfume-bottle.png", "icons/periscope.png", "icons/perpendicular-rings.png", "icons/person.png", "icons/perspective-dice-five.png", "icons/perspective-dice-four.png", "icons/perspective-dice-one.png", "icons/perspective-dice-six-faces-five.png", "icons/perspective-dice-six-faces-four.png", "icons/perspective-dice-six-faces-one.png", "icons/perspective-dice-six-faces-random.png", "icons/perspective-dice-six-faces-six.png", "icons/perspective-dice-six-faces-three.png", "icons/perspective-dice-six-faces-two.png", "icons/perspective-dice-six.png", "icons/perspective-dice-three.png", "icons/perspective-dice-two.png", "icons/pestle-mortar.png", "icons/pharoah.png", "icons/phone.png", "icons/photo-camera.png", "icons/phrygian-cap.png", "icons/piano-keys.png", "icons/pickelhaube.png", "icons/pie-chart.png", "icons/pie-slice.png", "icons/piece-skull.png", "icons/pierced-body.png", "icons/pierced-heart.png", "icons/piercing-sword.png", "icons/pig-face.png", "icons/pig.png", "icons/piggy-bank.png", "icons/pikeman.png", "icons/pill-drop.png", "icons/pill.png", "icons/pillow.png", "icons/pin.png", "icons/pinball-flipper.png", "icons/pincers.png", "icons/pine-tree.png", "icons/pineapple.png", "icons/ping-pong-bat.png", "icons/pipes.png", "icons/piranha.png", "icons/pirate-captain.png", "icons/pirate-coat.png", "icons/pirate-flag.png", "icons/pirate-grave.png", "icons/pirate-hat.png", "icons/pirate-hook.png", "icons/pirate-skull.png", "icons/pisa-tower.png", "icons/pisces.png", "icons/pistol-gun.png", "icons/pizza-cutter.png", "icons/pizza-slice.png", "icons/plague-doctor-profile.png", "icons/plain-arrow.png", "icons/plain-dagger.png", "icons/plane-wing.png", "icons/planet-core.png", "icons/planks.png", "icons/plants-and-animals.png", "icons/plasma-bolt.png", "icons/plastron.png", "icons/plate-claw.png", "icons/platform.png", "icons/play-button.png", "icons/plesiosaurus.png", "icons/plow.png", "icons/plug.png", "icons/pocket-bow.png", "icons/pocket-radio.png", "icons/pocket-watch.png", "icons/podium-second.png", "icons/podium-third.png", "icons/podium-winner.png", "icons/podium.png", "icons/pointing.png", "icons/pointy-hat.png", "icons/pointy-sword.png", "icons/poison-bottle.png", "icons/poison-cloud.png", "icons/poison-gas.png", "icons/poison.png", "icons/pokecog.png", "icons/poker-hand.png", "icons/polar-bear.png", "icons/police-badge.png", "icons/police-officer-head.png", "icons/police-target.png", "icons/pollen-dust.png", "icons/polo-shirt.png", "icons/pool-dive.png", "icons/pope-crown.png", "icons/poppy.png", "icons/porcelain-vase.png", "icons/portal.png", "icons/portculis.png", "icons/position-marker.png", "icons/potato.png", "icons/potion-ball.png", "icons/pounce.png", "icons/pouring-chalice.png", "icons/powder-bag.png", "icons/powder.png", "icons/power-button.png", "icons/power-generator.png", "icons/power-lightning.png", "icons/prayer-beads.png", "icons/prayer.png", "icons/praying-mantis.png", "icons/present.png", "icons/pretty-fangs.png", "icons/pretzel.png", "icons/price-tag.png", "icons/prisoner.png", "icons/private-first-class.png", "icons/private.png", "icons/processor.png", "icons/profit.png", "icons/propeller-beanie.png", "icons/protection-glasses.png", "icons/pschent-double-crown.png", "icons/psychic-waves.png", "icons/pterodactylus.png", "icons/pteruges.png", "icons/public-speaker.png", "icons/pull.png", "icons/pulley-hook.png", "icons/pulse.png", "icons/pummeled.png", "icons/pumpkin-lantern.png", "icons/pumpkin-mask.png", "icons/punch-blast.png", "icons/punch.png", "icons/puppet.png", "icons/push.png", "icons/puzzle.png", "icons/pyromaniac.png", "icons/quake-stomp.png", "icons/queen-crown.png", "icons/quick-slash.png", "icons/quicksand.png", "icons/quill-ink.png", "icons/quill.png", "icons/quiver.png", "icons/rabbit.png", "icons/race-car.png", "icons/radar-dish.png", "icons/radar-sweep.png", "icons/radial-balance.png", "icons/radio-tower.png", "icons/radioactive.png", "icons/ragged-wound.png", "icons/railway.png", "icons/rainbow-star.png", "icons/raining.png", "icons/raise-skeleton.png", "icons/raise-zombie.png", "icons/rake.png", "icons/rally-the-troops.png", "icons/ram.png", "icons/rank-1.png", "icons/rank-2.png", "icons/rank-3.png", "icons/rapidshare-arrow.png", "icons/raspberry.png", "icons/rattlesnake.png", "icons/raven.png", "icons/ray-gun.png", "icons/razor-blade.png", "icons/reactor.png", "icons/read.png", "icons/reaper-scythe.png", "icons/rear-aura.png", "icons/recycle.png", "icons/refinery.png", "icons/regeneration.png", "icons/relationship-bounds.png", "icons/relic-blade.png", "icons/reptile-tail.png", "icons/resize.png", "icons/resonance.png", "icons/reticule.png", "icons/retro-controller.png", "icons/return-arrow.png", "icons/revolt.png", "icons/revolver.png", "icons/rhinoceros-horn.png", "icons/ribbon-medal.png", "icons/ribbon.png", "icons/ribcage.png", "icons/rifle.png", "icons/ring.png", "icons/ringed-beam.png", "icons/ringed-planet.png", "icons/ringing-alarm.png", "icons/ringing-bell.png", "icons/riot-shield.png", "icons/river.png", "icons/road.png", "icons/roast-chicken.png", "icons/robber-hand.png", "icons/robber-mask.png", "icons/robber.png", "icons/robe.png", "icons/robin-hood-hat.png", "icons/robot-antennas.png", "icons/robot-golem.png", "icons/robot-leg.png", "icons/rock.png", "icons/rocket-flight.png", "icons/rocket-thruster.png", "icons/rocket.png", "icons/rogue.png", "icons/rolled-cloth.png", "icons/rolling-bomb.png", "icons/rolling-dice-cup.png", "icons/rolling-dices.png", "icons/rolling-energy.png", "icons/roman-shield.png", "icons/roman-toga.png", "icons/root-tip.png", "icons/rope-coil.png", "icons/ropeway.png", "icons/rosa-shield.png", "icons/rose.png", "icons/rough-wound.png", "icons/round-bottom-flask.png", "icons/round-shield.png", "icons/round-silo.png", "icons/round-star.png", "icons/round-straw-bale.png", "icons/round-struck.png", "icons/royal-love.png", "icons/rss.png", "icons/rugby-conversion.png", "icons/run.png", "icons/rune-stone.png", "icons/rune-sword.png", "icons/running-ninja.png", "icons/running-shoe.png", "icons/rupee.png", "icons/s-brick.png", "icons/saber-slash.png", "icons/saber-tooth.png", "icons/sabers-choc.png", "icons/sacrificial-dagger.png", "icons/sad-crab.png", "icons/saddle.png", "icons/safety-pin.png", "icons/sagittarius.png", "icons/sai.png", "icons/sailboat.png", "icons/saint-basil-cathedral.png", "icons/saiyan-suit.png", "icons/salamander.png", "icons/salmon.png", "icons/saloon-doors.png", "icons/salt-shaker.png", "icons/sand-castle.png", "icons/sand-snake.png", "icons/sands-of-time.png", "icons/saphir.png", "icons/sarcophagus.png", "icons/sattelite.png", "icons/save.png", "icons/saw-claw.png", "icons/sawed-off-shotgun.png", "icons/saxophone.png", "icons/scabbard.png", "icons/scale-mail.png", "icons/scales.png", "icons/scallop.png", "icons/scalpel-strike.png", "icons/scalpel.png", "icons/scar-wound.png", "icons/scarab-beetle.png", "icons/scarecrow.png", "icons/scissors.png", "icons/scooter.png", "icons/scorpio.png", "icons/scorpion-tail.png", "icons/scorpion.png", "icons/screaming.png", "icons/screen-impact.png", "icons/screwdriver.png", "icons/scroll-unfurled.png", "icons/scuba-tanks.png", "icons/scythe.png", "icons/sea-creature.png", "icons/sea-dragon.png", "icons/sea-serpent.png", "icons/sea-star.png", "icons/seahorse.png", "icons/seated-mouse.png", "icons/secret-door.png", "icons/select.png", "icons/self-love.png", "icons/sell-card.png", "icons/semi-closed-eye.png", "icons/sensuousness.png", "icons/sentry-gun.png", "icons/sergeant.png", "icons/serrated-slash.png", "icons/seven-pointed-star.png", "icons/severed-hand.png", "icons/sewed-shell.png", "icons/sewing-needle.png", "icons/sextant.png", "icons/shadow-follower.png", "icons/shadow-grasp.png", "icons/shaking-hands.png", "icons/shambling-mound.png", "icons/shambling-zombie.png", "icons/shard-sword.png", "icons/shark-fin.png", "icons/shark-jaws.png", "icons/sharp-axe.png", "icons/sharp-crown.png", "icons/sharp-halberd.png", "icons/sharp-lips.png", "icons/sharp-shuriken.png", "icons/sharp-smile.png", "icons/sharped-teeth-skull.png", "icons/shatter.png", "icons/shattered-glass.png", "icons/shattered-sword.png", "icons/sheep.png", "icons/sheikah-eye.png", "icons/shepherds-crook.png", "icons/sherlock-holmes.png", "icons/shield-bash.png", "icons/shield-bounces.png", "icons/shield-disabled.png", "icons/shield-echoes.png", "icons/shield-reflect.png", "icons/shield.png", "icons/shieldcomb.png", "icons/shining-claw.png", "icons/shining-heart.png", "icons/shining-sword.png", "icons/shinto-shrine-mirror.png", "icons/shinto-shrine.png", "icons/shiny-apple.png", "icons/shiny-entrance.png", "icons/shiny-iris.png", "icons/shiny-omega.png", "icons/shiny-purse.png", "icons/ship-wheel.png", "icons/ship-wreck.png", "icons/shirt.png", "icons/shop.png", "icons/shopping-cart.png", "icons/shorts.png", "icons/shotgun-rounds.png", "icons/shotgun.png", "icons/shoulder-armor.png", "icons/shoulder-scales.png", "icons/shouting.png", "icons/shower.png", "icons/shrug.png", "icons/shuriken-aperture.png", "icons/shuriken.png", "icons/shut-rose.png", "icons/shuttlecock.png", "icons/sickle.png", "icons/sideswipe.png", "icons/siege-ram.png", "icons/siege-tower.png", "icons/sight-disabled.png", "icons/silence.png", "icons/silenced.png", "icons/sing.png", "icons/sinking-ship.png", "icons/sinking-trap.png", "icons/sinusoidal-beam.png", "icons/siren.png", "icons/six-eyes.png", "icons/skeletal-hand.png", "icons/skeleton-inside.png", "icons/skeleton-key.png", "icons/skeleton.png", "icons/ski-boot.png", "icons/skid-mark.png", "icons/skirt.png", "icons/skull-bolt.png", "icons/skull-crack.png", "icons/skull-crossed-bones.png", "icons/skull-in-jar.png", "icons/skull-mask.png", "icons/skull-ring.png", "icons/skull-sabertooth.png", "icons/skull-shield.png", "icons/skull-signet.png", "icons/skull-slices.png", "icons/skull-staff.png", "icons/slalom.png", "icons/slap.png", "icons/slashed-shield.png", "icons/slavery-whip.png", "icons/sleepy.png", "icons/sleeveless-jacket.png", "icons/sliced-bread.png", "icons/slicing-arrow.png", "icons/slime.png", "icons/slingshot.png", "icons/slow-blob.png", "icons/sly.png", "icons/small-fire.png", "icons/smartphone.png", "icons/smash-arrows.png", "icons/smitten.png", "icons/smoke-bomb.png", "icons/smoking-finger.png", "icons/smoking-orb.png", "icons/smoking-pipe.png", "icons/smoking-volcano.png", "icons/snail-eyes.png", "icons/snail.png", "icons/snake-bite.png", "icons/snake-egg.png", "icons/snake-tongue.png", "icons/snake-totem.png", "icons/snake.png", "icons/snatch.png", "icons/sniffing-dog.png", "icons/snitch-quidditch-ball.png", "icons/snorkel.png", "icons/snow-bottle.png", "icons/snowflake-1.png", "icons/snowflake-2.png", "icons/snowing.png", "icons/snowman.png", "icons/soap-experiment.png", "icons/soccer-ball.png", "icons/soccer-field.png", "icons/socks.png", "icons/soda-can.png", "icons/sofa.png", "icons/solar-power.png", "icons/solar-system.png", "icons/solid-leaf.png", "icons/sombrero.png", "icons/sonic-boom.png", "icons/sonic-lightning.png", "icons/sonic-screech.png", "icons/sonic-shout.png", "icons/sound-off.png", "icons/sound-on.png", "icons/sound-waves.png", "icons/south-africa-flag.png", "icons/south-africa.png", "icons/south-america.png", "icons/space-shuttle.png", "icons/space-suit.png", "icons/spaceship.png", "icons/spade-skull.png", "icons/spade.png", "icons/spades.png", "icons/spain.png", "icons/spanner.png", "icons/spark-spirit.png", "icons/sparkling-sabre.png", "icons/sparky-bomb.png", "icons/sparrow.png", "icons/spartan-helmet.png", "icons/spartan.png", "icons/spatter.png", "icons/spawn-node.png", "icons/speaker-off.png", "icons/speaker.png", "icons/spear-feather.png", "icons/spear-hook.png", "icons/spears.png", "icons/spectacle-lenses.png", "icons/spectacles.png", "icons/spectre-m4.png", "icons/spectre.png", "icons/speed-boat.png", "icons/speedometer.png", "icons/spell-book.png", "icons/sperm-whale.png", "icons/spider-alt.png", "icons/spider-bot.png", "icons/spider-face.png", "icons/spider-web.png", "icons/spikeball.png", "icons/spiked-armor.png", "icons/spiked-ball.png", "icons/spiked-bat.png", "icons/spiked-collar.png", "icons/spiked-fence.png", "icons/spiked-halo.png", "icons/spiked-mace.png", "icons/spiked-shell.png", "icons/spiked-snail.png", "icons/spiked-tail.png", "icons/spiked-tentacle.png", "icons/spiked-trunk.png", "icons/spikes-full.png", "icons/spikes-half.png", "icons/spikes-init.png", "icons/spikes.png", "icons/spiky-eclipse.png", "icons/spiky-explosion.png", "icons/spiky-field.png", "icons/spiky-pit.png", "icons/spill.png", "icons/spinal-coil.png", "icons/spine-arrow.png", "icons/spinning-blades.png", "icons/spinning-ribbons.png", "icons/spinning-sword.png", "icons/spinning-top.png", "icons/spiral-arrow.png", "icons/spiral-bloom.png", "icons/spiral-bottle.png", "icons/spiral-hilt.png", "icons/spiral-shell.png", "icons/spiral-tentacle.png", "icons/spiral-thrust.png", "icons/splash.png", "icons/splashy-stream.png", "icons/split-body.png", "icons/split-cross.png", "icons/splurt.png", "icons/spoon.png", "icons/spoted-flower.png", "icons/spotted-arrowhead.png", "icons/spotted-bug.png", "icons/spotted-mushroom.png", "icons/spotted-wound.png", "icons/spoutnik.png", "icons/spray.png", "icons/sprint.png", "icons/sprout-disc.png", "icons/sprout.png", "icons/spy.png", "icons/spyglass.png", "icons/square-bottle.png", "icons/squib.png", "icons/squid-head.png", "icons/squid.png", "icons/squirrel.png", "icons/sri-lanka.png", "icons/stabbed-note.png", "icons/stack.png", "icons/stag-head.png", "icons/stahlhelm.png", "icons/stairs.png", "icons/stake-hammer.png", "icons/stalagtite.png", "icons/standing-potion.png", "icons/star-cycle.png", "icons/star-formation.png", "icons/star-gate.png", "icons/star-prominences.png", "icons/star-pupil.png", "icons/star-sattelites.png", "icons/star-shuriken.png", "icons/star-skull.png", "icons/star-swirl.png", "icons/starfighter.png", "icons/stars-stack.png", "icons/staryu.png", "icons/static-guard.png", "icons/static-waves.png", "icons/static.png", "icons/steak.png", "icons/steam-locomotive.png", "icons/steam.png", "icons/steampunk-goggles.png", "icons/steel-claws.png", "icons/steel-door.png", "icons/steeltoe-boots.png", "icons/steelwing-emblem.png", "icons/steering-wheel.png", "icons/stegosaurus-scales.png", "icons/stethoscope.png", "icons/steyr-aug.png", "icons/stick-grenade.png", "icons/stick-splitting.png", "icons/sticking-plaster.png", "icons/stigmata.png", "icons/stiletto.png", "icons/stitched-wound.png", "icons/stomach.png", "icons/stomp-tornado.png", "icons/stomp.png", "icons/stone-axe.png", "icons/stone-block.png", "icons/stone-bridge.png", "icons/stone-bust.png", "icons/stone-crafting.png", "icons/stone-pile.png", "icons/stone-spear.png", "icons/stone-sphere.png", "icons/stone-stack.png", "icons/stone-tablet.png", "icons/stone-throne.png", "icons/stone-tower.png", "icons/stone-wall.png", "icons/stoned-skull.png", "icons/stopwatch.png", "icons/stork-delivery.png", "icons/strafe.png", "icons/strawberry.png", "icons/striking-arrows.png", "icons/striking-balls.png", "icons/striking-clamps.png", "icons/striking-diamonds.png", "icons/striking-splinter.png", "icons/striped-sun.png", "icons/striped-sword.png", "icons/strong.png", "icons/stump-regrowth.png", "icons/stun-grenade.png", "icons/submarine.png", "icons/suckered-tentacle.png", "icons/sugar-cane.png", "icons/suicide.png", "icons/suitcase.png", "icons/suits.png", "icons/sun-cloud.png", "icons/sun-radiations.png", "icons/sun-spear.png", "icons/sun.png", "icons/sunbeams.png", "icons/sundial.png", "icons/sunflower.png", "icons/sunglasses.png", "icons/sunken-eye.png", "icons/sunrise.png", "icons/sunset.png", "icons/super-mushroom.png", "icons/supersonic-arrow.png", "icons/supersonic-bullet.png", "icons/surprised-skull.png", "icons/surprised.png", "icons/surrounded-eye.png", "icons/surrounded-shield.png", "icons/suspicious.png", "icons/sverd-i-fjell.png", "icons/swallow.png", "icons/swamp.png", "icons/swan-breeze.png", "icons/swan.png", "icons/swap-bag.png", "icons/swimfins.png", "icons/swirl-ring.png", "icons/swirl-string.png", "icons/swirled-shell.png", "icons/swiss-army-knife.png", "icons/switch-weapon.png", "icons/switchblade.png", "icons/switzerland.png", "icons/sword-altar.png", "icons/sword-array.png", "icons/sword-break.png", "icons/sword-clash.png", "icons/sword-hilt.png", "icons/sword-in-stone.png", "icons/sword-slice.png", "icons/sword-smithing.png", "icons/sword-spade.png", "icons/sword-spin.png", "icons/sword-tie.png", "icons/sword-wound.png", "icons/swordman.png", "icons/swords-emblem.png", "icons/swordwoman.png", "icons/syringe.png", "icons/t-brick.png", "icons/tabi-boot.png", "icons/table.png", "icons/tablet.png", "icons/tabletop-players.png", "icons/tacos.png", "icons/take-my-money.png", "icons/talk.png", "icons/tank-tread.png", "icons/tank.png", "icons/tap.png", "icons/target-arrows.png", "icons/target-dummy.png", "icons/target-laser.png", "icons/target-shot.png", "icons/targeted.png", "icons/targeting.png", "icons/tattered-banner.png", "icons/taurus.png", "icons/teacher.png", "icons/teapot-leaves.png", "icons/teapot.png", "icons/tear-tracks.png", "icons/tearing.png", "icons/tec-9.png", "icons/techno-heart.png", "icons/telefrag.png", "icons/telepathy.png", "icons/teleport.png", "icons/telescopic-baton.png", "icons/teller-mine.png", "icons/templar-eye.png", "icons/templar-heart.png", "icons/templar-shield.png", "icons/temptation.png", "icons/tennis-ball.png", "icons/tension-snowflake.png", "icons/tentacle-heart.png", "icons/tentacle-strike.png", "icons/tentacles-skull.png", "icons/tentacurl.png", "icons/terror.png", "icons/tesla-coil.png", "icons/tesla-turret.png", "icons/tesla.png", "icons/test-tubes.png", "icons/theater-curtains.png", "icons/thermometer-cold.png", "icons/thermometer-hot.png", "icons/thermometer-scale.png", "icons/think.png", "icons/third-eye.png", "icons/thompson-m1.png", "icons/thompson-m1928.png", "icons/thor-fist.png", "icons/thor-hammer.png", "icons/thorn-helix.png", "icons/thorned-arrow.png", "icons/thorny-tentacle.png", "icons/thorny-vine.png", "icons/three-burning-balls.png", "icons/three-keys.png", "icons/three-leaves.png", "icons/three-pointed-shuriken.png", "icons/throne-king.png", "icons/thrown-charcoal.png", "icons/thrown-daggers.png", "icons/thrown-knife.png", "icons/thrown-spear.png", "icons/thrust-bend.png", "icons/thrust.png", "icons/thumb-down.png", "icons/thumb-up.png", "icons/thunder-blade.png", "icons/thunder-skull.png", "icons/thunder-struck.png", "icons/thunderball.png", "icons/tiara.png", "icons/tic-tac-toe.png", "icons/tick.png", "icons/tie.png", "icons/tied-scroll.png", "icons/tiger-head.png", "icons/tiger.png", "icons/tightrope.png", "icons/time-bomb.png", "icons/time-trap.png", "icons/tinker.png", "icons/tipi.png", "icons/tire-tracks.png", "icons/toad-teeth.png", "icons/toaster.png", "icons/tomahawk.png", "icons/tombstone.png", "icons/tongue.png", "icons/tooth.png", "icons/top-hat.png", "icons/top-paw.png", "icons/topaz.png", "icons/torch.png", "icons/tornado-discs.png", "icons/tornado.png", "icons/torpedo.png", "icons/tortoise.png", "icons/totem-head.png", "icons/totem-mask.png", "icons/totem.png", "icons/toucan.png", "icons/tower-bridge.png", "icons/tower-fall.png", "icons/tower-flag.png", "icons/toy-mallet.png", "icons/tracked-robot.png", "icons/trade.png", "icons/traffic-cone.png", "icons/traffic-lights-green.png", "icons/traffic-lights-orange.png", "icons/traffic-lights-ready-to-go.png", "icons/traffic-lights-red.png", "icons/trample.png", "icons/transform.png", "icons/transfuse.png", "icons/transparent-slime.png", "icons/transparent-tubes.png", "icons/transportation-rings.png", "icons/trap-mask.png", "icons/trash-can.png", "icons/tread.png", "icons/treasure-map.png", "icons/trebuchet.png", "icons/tree-beehive.png", "icons/tree-branch.png", "icons/tree-face.png", "icons/tree-swing.png", "icons/trefoil-lily.png", "icons/trefoil-shuriken.png", "icons/trench-assault.png", "icons/trench-body-armor.png", "icons/trench-knife.png", "icons/trench-spade.png", "icons/tribal-mask.png", "icons/tribal-pendant.png", "icons/triceratops-head.png", "icons/trident.png", "icons/triforce.png", "icons/trigger-hurt.png", "icons/trilobite.png", "icons/triorb.png", "icons/triple-beak.png", "icons/triple-claws.png", "icons/triple-corn.png", "icons/triple-lock.png", "icons/triple-needle.png", "icons/triple-plier.png", "icons/triple-scratches.png", "icons/triple-shells.png", "icons/triple-skulls.png", "icons/triple-yin.png", "icons/tripwire.png", "icons/triquetra.png", "icons/trireme.png", "icons/triton-head.png", "icons/trojan-horse.png", "icons/troll.png", "icons/tron-arrow.png", "icons/trophy-cup.png", "icons/trophy.png", "icons/tropical-fish.png", "icons/trousers.png", "icons/trowel.png", "icons/truck.png", "icons/trumpet.png", "icons/tumor.png", "icons/tumulus.png", "icons/tune-pitch.png", "icons/turban.png", "icons/turd.png", "icons/turret.png", "icons/turtle-shell.png", "icons/turtle.png", "icons/tv-remote.png", "icons/tv-tower.png", "icons/tv.png", "icons/twin-shell.png", "icons/twirl-center.png", "icons/twirly-flower.png", "icons/twister.png", "icons/two-feathers.png", "icons/two-handed-sword.png", "icons/two-shadows.png", "icons/tyre.png", "icons/ubisoft-sun.png", "icons/udder.png", "icons/ufo.png", "icons/ultrasound.png", "icons/umbrella-bayonet.png", "icons/umbrella.png", "icons/uncertainty.png", "icons/underhand.png", "icons/underwear.png", "icons/unfriendly-fire.png", "icons/unicorn.png", "icons/unicycle.png", "icons/union-jack.png", "icons/unlit-bomb.png", "icons/unlit-candelabra.png", "icons/unlocking.png", "icons/unplugged.png", "icons/unstable-orb.png", "icons/unstable-projectile.png", "icons/up-card.png", "icons/upgrade.png", "icons/uprising.png", "icons/ursa-major.png", "icons/uruguay.png", "icons/usa-flag.png", "icons/usable.png", "icons/usb-key.png", "icons/uzi.png", "icons/vacuum-cleaner.png", "icons/valley.png", "icons/vampire-cape.png", "icons/vampire-dracula.png", "icons/van-damme-split.png", "icons/vanilla-flower.png", "icons/velocipede.png", "icons/velociraptor-tracks.png", "icons/velociraptor.png", "icons/vertical-banner.png", "icons/vertical-flip.png", "icons/vial.png", "icons/vibrating-ball.png", "icons/vibrating-shield.png", "icons/video-camera.png", "icons/viking-church.png", "icons/viking-head.png", "icons/viking-helmet.png", "icons/viking-longhouse.png", "icons/viking-shield.png", "icons/vile-fluid.png", "icons/village.png", "icons/vine-flower.png", "icons/vine-leaf.png", "icons/vine-whip.png", "icons/vintage-robot.png", "icons/viola.png", "icons/virgo.png", "icons/virtual-marker.png", "icons/virus.png", "icons/visored-helm.png", "icons/vitruvian-man.png", "icons/volcano.png", "icons/volleyball-ball.png", "icons/vomiting.png", "icons/voodoo-doll.png", "icons/vortex.png", "icons/vote.png", "icons/vulture.png", "icons/walkie-talkie.png", "icons/walking-boot.png", "icons/walking-scout.png", "icons/wall-light.png", "icons/walrus-head.png", "icons/walther-ppk.png", "icons/war-axe.png", "icons/war-pick.png", "icons/warlock-eye.png", "icons/warlock-hood.png", "icons/warp-pipe.png", "icons/wasp-sting.png", "icons/watch.png", "icons/watchtower.png", "icons/water-bolt.png", "icons/water-drop.png", "icons/water-fountain.png", "icons/water-splash.png", "icons/watering-can.png", "icons/watermelon.png", "icons/wave-crest.png", "icons/wave-strike.png", "icons/wavy-chains.png", "icons/wavy-itinerary.png", "icons/wax-seal.png", "icons/wax-tablet.png", "icons/web-spit.png", "icons/weight-crush.png", "icons/weight-lifting-down.png", "icons/weight-lifting-up.png", "icons/weight.png", "icons/well.png", "icons/werewolf.png", "icons/western-hat.png", "icons/whale-tail.png", "icons/wheat.png", "icons/wheelbarrow.png", "icons/whip.png", "icons/whiplash.png", "icons/whirlpool-shuriken.png", "icons/whirlwind.png", "icons/whistle.png", "icons/white-book.png", "icons/white-cat.png", "icons/white-tower.png", "icons/wide-arrow-dunk.png", "icons/wildfires.png", "icons/william-tell-skull.png", "icons/william-tell.png", "icons/winchester-rifle.png", "icons/wind-hole.png", "icons/wind-slap.png", "icons/wind-turbine.png", "icons/windmill.png", "icons/window-bars.png", "icons/windpump.png", "icons/windy-stripes.png", "icons/wine-bottle.png", "icons/wine-glass.png", "icons/wing-cloak.png", "icons/winged-arrow.png", "icons/winged-emblem.png", "icons/winged-leg.png", "icons/winged-shield.png", "icons/winged-sword.png", "icons/wingfoot.png", "icons/witch-face.png", "icons/witch-flight.png", "icons/wizard-face.png", "icons/wizard-staff.png", "icons/wolf-head.png", "icons/wolf-howl.png", "icons/wolf-trap.png", "icons/wolverine-claws.png", "icons/woman-elf-face.png", "icons/wood-axe.png", "icons/wood-cabin.png", "icons/wood-club.png", "icons/wood-pile.png", "icons/wooden-clogs.png", "icons/wooden-crate.png", "icons/wooden-door.png", "icons/wooden-fence.png", "icons/wooden-helmet.png", "icons/wooden-pegleg.png", "icons/wooden-pier.png", "icons/wooden-sign.png", "icons/wool.png", "icons/world.png", "icons/worm-mouth.png", "icons/worried-eyes.png", "icons/wrapped-heart.png", "icons/wrapped-sweet.png", "icons/wrapping-star.png", "icons/wrecking-ball.png", "icons/wrench.png", "icons/wyvern.png", "icons/xylophone.png", "icons/yin-yang.png", "icons/z-brick.png", "icons/zebra-shield.png", "icons/zeppelin.png", "icons/zeus-sword.png", "icons/zig-arrow.png", "icons/zigzag-cage.png", "icons/zigzag-leaf.png", "icons/zigzag-tune.png", "icons/zipper.png"};
        int size = icons.length;
        Random random = new Random();
        String image = "images/" + icons[abs(random.nextInt()) % size];
        if(action.equals("user")) {
            url = "/user.jsp";
        } else if(action.equals("edit_notifications")) {
            try {
                String driver = secure.DBConnection.driver;
                Class.forName(driver);
                String dbURL = secure.DBConnection.dbURL;
                String user = secure.DBConnection.username;
                String pass = secure.DBConnection.password;
                Connection connection = DriverManager.getConnection(dbURL, user, pass);

                if(request.getParameter(Integer.toString(0)) != null && request.getParameter(Integer.toString(0)).equals("delete_all")) {
                    String query;
                    PreparedStatement ps;
                    query = "DELETE FROM `" + secure.DBStructure.table15 + "` WHERE owner = ?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, username);
                    ps.executeUpdate();
                    ps.close();
                }
                
                else {
                    ArrayList<Integer> selected = new ArrayList<Integer>();
                    int num = 1;
                    while(true) {
                        String notificationId = request.getParameter(Integer.toString(num));
                        if((notificationId == null || notificationId.equals(""))) {
                            num++;
                            continue;
                        }
                        else if(notificationId.equals("ENDTOKEN")) {
                            break;
                        }
                        selected.add(Integer.parseInt(request.getParameter(Integer.toString(num))));
                        num++;
                    }

                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table15 + "` WHERE owner = '" + username + "'");

                    while (rs.next()) {
                        if(!selected.contains(rs.getInt("id"))) {
                            continue;
                        }

                        String query;
                        PreparedStatement ps;

                        query = "DELETE FROM `" + secure.DBStructure.table15 + "` WHERE id = ?";
                        ps = connection.prepareStatement(query);
                        ps.setInt(1, rs.getInt("id"));
                        ps.executeUpdate();
                        ps.close();
                    }
                }
                
                url = "/notifications.jsp";
                
                connection.close();
            } catch (ClassNotFoundException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(action.equals("profile")) {
            if(username.equals("")) {
                request.setAttribute("username", "error: propted redirect");
                url = "/login.jsp";
            }
            else {
                url = "/profile.jsp";
            }
        } else if(action.equals("edit_picture")) {
            url = "/edit_picture.jsp";
        } else if(action.equals("update_picture")) {
            String picture = request.getParameter("picture");
            try {
                String driver = secure.DBConnection.driver;
                Class.forName(driver);
                String dbURL = secure.DBConnection.dbURL;
                String user = secure.DBConnection.username;
                String pass = secure.DBConnection.password;
                Connection connection = DriverManager.getConnection(dbURL, user, pass);

                String query = "UPDATE `" + secure.DBStructure.table16 + "` SET picture='" + picture + "' WHERE username='" + username + "'";
                PreparedStatement ps = connection.prepareStatement(query);

                ps.executeUpdate();
                ps.close();

                request.setAttribute("username", username);
                url = "/profile.jsp";
                
                connection.close();
            } catch (ClassNotFoundException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(action.equals("edit_profile")) {
            url = "/edit_profile.jsp";
        } else if(action.equals("create")) {
            String confirm = request.getParameter("confirm");
            confirm = secure.DBConnection.SALT + confirm;
            confirm = secure.DBConnection.generateHash(confirm);
            if((username.length() <= 16) && password.equals(confirm)) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String bio = request.getParameter("bio");
                try {
                    String driver = secure.DBConnection.driver;
                    Class.forName(driver);
                    String dbURL = secure.DBConnection.dbURL;
                    String user = secure.DBConnection.username;
                    String pass = secure.DBConnection.password;
                    Connection connection = DriverManager.getConnection(dbURL, user, pass);

                    /* check if the username is in use */
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table16 + "`");
                    boolean error = false;
                    while(rs.next()) {
                        String tmpUsername = rs.getString("username");
                        if(tmpUsername.equals(username) || username.equals("null")) {
                            error = true;
                            break;
                        }
                    }
                    if(!error) {
                        boolean repeat = true;
                        while(repeat) {
                            statement = connection.createStatement();
                            rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table16 + "` WHERE picture='" + image + "'");
                            if (rs.next()) {
                                image = "images/" + icons[abs(random.nextInt()) % size];
                            }
                            else {
                                repeat = false;
                            }
                            rs.close();
                        }
                        
                        java.util.Date date = new Date();
                        Object dateJoined = new java.sql.Timestamp(date.getTime());

                        String query = "INSERT INTO `" + secure.DBStructure.table16 + "` (`username`, `password`, `picture`, `email`, `name`, `joined`, `bio`, `notifications`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

                        PreparedStatement ps = connection.prepareStatement(query);

                        ps.setString(1, username);
                        ps.setString(2, password);
                        ps.setString(3, image);
                        ps.setString(4, email);
                        ps.setString(5, name);
                        ps.setObject(6, dateJoined);
                        ps.setString(7, bio);
                        ps.setString(8, "11111");
                        ps.execute();
                        ps.close();
                        
                        /* send email notification */
                        String subject = "Welcome to CardCollector!";
                        String content = "Greetings from CardCollector!\n\n"
                                + "Welcome to CardCollector!\n\n"
                                + "It is a pleasure to have you join our family of users."
                                + " As a first-time user of this website, we ask you to please select your personal profile picture using the picture icon button on the Profile Page."
                                + " If you would like to know how CardCollector was created, or if you encounter any problems or have suggestions on how to improve the site, please use the information on the Help Page."
                                + " On the Help Page you will also find information that will help you to use CardCollector to help you to log decks or collections of your Magic The Gathering cards!\n\n"
                                + "Please remember that CardCollector was not created to gain anything monetarily, and is solely for the purpose of helping you get the most of your Magic The Gathering cards."
                                + " We own none of the material displayed on CardCollector, nor do we take responsibility for content put on the site."
                                + " For more information on the Terms of Service, visit http://mtg.cardcollector.org/terms.jsp.\n\n"
                                + "Happy Collecting!\n\n\n"
                                + "Sincerely,\n"
                                + "Wesley Harris, Creator of CardCollector\n"
                                + "http://mtg.cardcollector.org\n"
                                + "http://markwesleyharris.com\n\n"
                                + "Please do not reply to this email, it was sent from an unattended mailbox.";

                        try {
                            EmailUtility.sendEmail(email, subject, content);
                        } catch (Exception ex) {
                            request.setAttribute("username", "");
                            url = "/index.jsp";
                            request.setAttribute("error", ex);
                            Logger.getLogger(PopupServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        request.setAttribute("username", username);
                        Cookie cookie = new Cookie("username", username);
                        cookie.setMaxAge(60*60*24*7);
                        response.addCookie(cookie);
                
                        url = "/profile.jsp";
                    }
                    else {
                        if(username.equals("null")) {
                            request.setAttribute("username", "error: chosen reserved username");
                        }
                        else {
                            request.setAttribute("username", "error: username already in use");
                        }
                        
                        request.setAttribute("name", request.getParameter("name"));
                        request.setAttribute("email", request.getParameter("email"));
                        request.setAttribute("password", request.getParameter("password"));
                        request.setAttribute("confirm", request.getParameter("confirm"));
                        url = "/register.jsp";
                    }
                    rs.close();
                    connection.close();
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("username", "");
                    url = "/index.jsp";
                    request.setAttribute("error", ex);
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    request.setAttribute("username", "");
                    url = "/index.jsp";
                    request.setAttribute("error", ex);
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if(username.length() > 16) {
                    request.setAttribute("username", "error: username is too long");
                }
                else if(password.length() > 24) {
                    request.setAttribute("username", "error: password is too long");
                }
                else {
                    request.setAttribute("username", "error: password mismatch");
                }
                
                request.setAttribute("name", request.getParameter("name"));
                request.setAttribute("email", request.getParameter("email"));
                request.setAttribute("password", request.getParameter("password"));
                request.setAttribute("confirm", request.getParameter("confirm"));
                url = "/register.jsp";
            }
        } else if(action.equals("register")) {
            url = "/register.jsp";
        } else if(action.equals("login")) {
            request.setAttribute("username", null);
            url = "/login.jsp";
        } else if(action.equals("logout")) {
            request.setAttribute("username", "");
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {            
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("username")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            url = "/login.jsp";
        } else if(action.equals("validate")) {
            UserInfo userInfo = new UserInfo();
            UserInfo user = (UserInfo) userInfo.getUser(username);
            if(user == null) {
                request.setAttribute("username", "error: user does not exist");
                url = "/login.jsp";
            }
            else if(user.getPassword().equals(password)) {
                request.setAttribute("username", user.getUsername());
                Cookie cookie = new Cookie("username", user.getUsername());
                cookie.setMaxAge(60*60*24*7);
                response.addCookie(cookie);
                url = "/index.jsp";
            }
            else {
                request.setAttribute("username", "error: invalid credentials");
                url = "/login.jsp";
            }
        } else if(action.equals("submit_edit")) {
            String confirm = request.getParameter("confirm");
            confirm = secure.DBConnection.SALT + confirm;
            confirm = secure.DBConnection.generateHash(confirm);
            String newUser = request.getParameter("new_user");
            String name = request.getParameter("name");
            String bio = request.getParameter("bio");
            String deckc = request.getParameter("deck_comment");
            String collectionc = request.getParameter("collection_comment");
            String cardcr = request.getParameter("card_comment_reaction");
            String deckcr = request.getParameter("deck_comment_reaction");
            String collectioncr = request.getParameter("collection_comment_reaction");
            if (newUser == null || newUser.equals(""))
                newUser = username;
            boolean error = false;
            if(password != null && !password.equals("")) {
                if(!password.equals(confirm)) {
                    error = true;
                }
            }
            if((newUser.length() <= 16) && !error) {

                String pic = request.getParameter("pic");
                String email = request.getParameter("email");
                try {
                    String driver = secure.DBConnection.driver;
                    Class.forName(driver);
                    String dbURL = secure.DBConnection.dbURL;
                    String user = secure.DBConnection.username;
                    String pass = secure.DBConnection.password;
                    Connection connection = DriverManager.getConnection(dbURL, user, pass);

                    String query;
                    PreparedStatement ps;

                    /* Username */
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table16 + "` WHERE username='" + newUser + "';");
                    if (!newUser.equals(username) && !rs.next()) {
                        query = "UPDATE `" + secure.DBStructure.table16 + "` SET username = ? WHERE username = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, newUser);
                        ps.setString(2, username);
                        ps.executeUpdate();
                    }
                    else {
                        newUser = username;
                    }
                    rs.close();

                    /* Name */
                    if (name != null && !name.equals("")) {
                        query = "UPDATE `" + secure.DBStructure.table16 + "` SET name = ? WHERE username = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, name);
                        ps.setString(2, newUser);
                        ps.executeUpdate();
                    }

                    /* Picture */
                    if (pic != null && pic.equals("update")) {
                        boolean repeat = true;
                        while(repeat) {
                            statement = connection.createStatement();
                            rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table16 + "` WHERE picture='" + image + "'");
                            if (rs.next()) {
                                image = "images/" + icons[abs(random.nextInt()) % size];
                            }
                            else {
                                repeat = false;
                            }
                            rs.close();
                        }
                        query = "UPDATE `" + secure.DBStructure.table16 + "` SET picture = ? WHERE username = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, image);
                        ps.setString(2, newUser);
                        ps.executeUpdate();
                    }

                    /* Password */
                    if (password != null && !password.equals("")) {
                        query = "UPDATE `" + secure.DBStructure.table16 + "` SET password = ? WHERE username = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, password);
                        ps.setString(2, newUser);
                        ps.executeUpdate();
                    }

                    /* Email */
                    if (email != null && !email.equals("")) {
                        query = "UPDATE `" + secure.DBStructure.table16 + "` SET email = ? WHERE username = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, email);
                        ps.setString(2, newUser);
                        ps.executeUpdate();
                    }
                    
                    /* Bio */
                    if (bio != null && !bio.equals("")) {
                        query = "UPDATE `" + secure.DBStructure.table16 + "` SET bio = ? WHERE username = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, bio);
                        ps.setString(2, newUser);
                        ps.executeUpdate();
                    }
                    
                    /* Notifications */
                    String notifications = "";
                    if (deckc != null && deckc.equals("receive")) {
                        notifications += '1';
                    }
                    else {
                        notifications += '0';
                    }
                    if (collectionc != null && collectionc.equals("receive")) {
                        notifications += '1';
                    }
                    else {
                        notifications += '0';
                    }
                    if (cardcr != null && cardcr.equals("receive")) {
                        notifications += '1';
                    }
                    else {
                        notifications += '0';
                    }
                    if (deckcr != null && deckcr.equals("receive")) {
                        notifications += '1';
                    }
                    else {
                        notifications += '0';
                    }
                    if (collectioncr != null && collectioncr.equals("receive")) {
                        notifications += '1';
                    }
                    else {
                        notifications += '0';
                    }
                    
                    query = "UPDATE `" + secure.DBStructure.table16 + "` SET notifications = ? WHERE username = ?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, notifications);
                    ps.setString(2, newUser);
                    ps.executeUpdate();

                    url = "/profile.jsp";
                    request.setAttribute("username", newUser);
                    connection.close();
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("username", "");
                    url = "/index.jsp";
                    request.setAttribute("error", ex);
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    request.setAttribute("username", "");
                    url = "/index.jsp";
                    request.setAttribute("error", ex);
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if(username.length() > 16) {
                    request.setAttribute("username", "error: username is too long");
                }
                else if(password.length() > 24) {
                    request.setAttribute("username", "error: password is too long");
                }
                else {
                    request.setAttribute("username", "error: password mismatch");
                }
                url = "/edit_profile.jsp";
            }
        } else if(action.equals("refresh_picture")) {
            try {
                String driver = secure.DBConnection.driver;
                Class.forName(driver);
                String dbURL = secure.DBConnection.dbURL;
                String user = secure.DBConnection.username;
                String pass = secure.DBConnection.password;
                Connection connection = DriverManager.getConnection(dbURL, user, pass);

                String query;
                PreparedStatement ps;
                Statement statement;
                ResultSet rs;

                boolean repeat = true;
                while(repeat) {
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table16 + "` WHERE picture='" + image + "'");
                    if (rs.next()) {
                        image = "images/" + icons[abs(random.nextInt()) % size];
                    }
                    else {
                        repeat = false;
                    }
                    rs.close();
                }
                query = "UPDATE `" + secure.DBStructure.table16 + "` SET picture = ? WHERE username = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, image);
                ps.setString(2, username);
                ps.executeUpdate();

                url = "/profile.jsp";
                connection.close();
            } catch (ClassNotFoundException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(action.equals("favorite")) {
            String id = request.getParameter("id");
            try {
                String driver = secure.DBConnection.driver;
                Class.forName(driver);
                String dbURL = secure.DBConnection.dbURL;
                String user = secure.DBConnection.username;
                String pass = secure.DBConnection.password;
                Connection connection = DriverManager.getConnection(dbURL, user, pass);

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table17 + "` WHERE user='" + username + "'");
                
                boolean error = false;
                while(rs.next()) {
                    if(rs.getString("user_id").equals(id)) {
                        error = true;
                    }
                }
                rs.close();
                
                rs = statement.executeQuery("SELECT * FROM `" + secure.DBStructure.table17 + "` ORDER BY id ASC");
                
                /* find the next possible id */
                int count = 1;
                while(rs.next()) {
                    if(rs.getInt("id") > count) {
                        break;
                    }
                    count++;
                }
                rs.close();
                
                if(!error) {
                    String query = "INSERT INTO `" + secure.DBStructure.table17 + "` (`id`, `user_id`, `user`) VALUES (?, ?, ?);";

                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setInt(1, count);
                    ps.setString(2, id);
                    ps.setString(3, username);

                    ps.execute();
                    ps.close();
                }
                else {
                    String query = "DELETE FROM `" + secure.DBStructure.table17 + "` WHERE user_id='" + id + "' AND user='" + username + "'";

                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.executeUpdate();
                    ps.close();
                }
                
                connection.close();
                url = "/profile.jsp";
            } catch (ClassNotFoundException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                request.setAttribute("username", "");
                url = "/index.jsp";
                request.setAttribute("error", ex);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(action.equals("notifications")) {
            url = "/notifications.jsp";
        } else {
            url = "/help.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
