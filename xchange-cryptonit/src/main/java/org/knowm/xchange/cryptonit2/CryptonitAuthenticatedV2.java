package org.knowm.xchange.cryptonit2;

import java.io.IOException;
import java.math.BigDecimal;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.cryptonit2.dto.CryptonitException;
import org.knowm.xchange.cryptonit2.dto.CryptonitTransferBalanceResponse;
import org.knowm.xchange.cryptonit2.dto.account.CryptonitWithdrawal;
import org.knowm.xchange.cryptonit2.dto.account.WithdrawalRequest;
import org.knowm.xchange.cryptonit2.dto.trade.CryptonitOrder;
import org.knowm.xchange.cryptonit2.dto.trade.CryptonitUserTransaction;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("api")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface CryptonitAuthenticatedV2 {

  @POST
  @Path("open_orders/{pair}/")
  CryptonitOrder[] getOpenOrders(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @PathParam("pair") org.knowm.xchange.cryptonit2.CryptonitV2.Pair pair)
      throws CryptonitException, IOException;

  @POST
  @Path("{side}/market/{pair}/")
  CryptonitOrder placeMarketOrder(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @PathParam("side") Side side,
      @PathParam("pair") CryptonitV2.Pair pair,
      @FormParam("amount") BigDecimal amount)
      throws CryptonitException, IOException;

  @POST
  @Path("{side}/{pair}/")
  CryptonitOrder placeOrder(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @PathParam("side") Side side,
      @PathParam("pair") CryptonitV2.Pair pair,
      @FormParam("amount") BigDecimal amount,
      @FormParam("price") BigDecimal price)
      throws CryptonitException, IOException;

  @POST
  @Path("user_transactions/")
  CryptonitUserTransaction[] getUserTransactions(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("limit") Long numberOfTransactions,
      @FormParam("offset") Long offset,
      @FormParam("sort") String sort)
      throws CryptonitException, IOException;

  @POST
  @Path("user_transactions/{pair}/")
  CryptonitUserTransaction[] getUserTransactions(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @PathParam("pair") CryptonitV2.Pair pair,
      @FormParam("limit") Long numberOfTransactions,
      @FormParam("offset") Long offset,
      @FormParam("sort") String sort)
      throws CryptonitException, IOException;

  @POST
  @Path("xrp_withdrawal/")
  CryptonitWithdrawal xrpWithdrawal(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("amount") BigDecimal amount,
      @FormParam("address") String rippleAddress,
      @FormParam("destination_tag") String destinationTag)
      throws CryptonitException, IOException;

  @POST
  @Path("ltc_withdrawal/")
  CryptonitWithdrawal withdrawLitecoin(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("amount") BigDecimal amount,
      @FormParam("address") String address)
      throws CryptonitException, IOException;

  @POST
  @Path("bch_withdrawal/")
  CryptonitWithdrawal bchWithdrawal(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("amount") BigDecimal amount,
      @FormParam("address") String address)
      throws CryptonitException, IOException;

  @POST
  @Path("eth_withdrawal/")
  CryptonitWithdrawal withdrawEther(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("amount") BigDecimal amount,
      @FormParam("address") String address)
      throws CryptonitException, IOException;

  @POST
  @Path("transfer-to-main/")
  CryptonitTransferBalanceResponse transferSubAccountBalanceToMain(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("amount") BigDecimal amount,
      @FormParam("currency") String currency,
      @FormParam("subAccount") String subAccount)
      throws CryptonitException, IOException;

  @POST
  @Path("withdrawal-requests/")
  WithdrawalRequest[] getWithdrawalRequests(
      @FormParam("key") String apiKey,
      @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("timedelta") Long timeDelta)
      throws CryptonitException, IOException;

  enum Side {
    buy,
    sell
  }
}
